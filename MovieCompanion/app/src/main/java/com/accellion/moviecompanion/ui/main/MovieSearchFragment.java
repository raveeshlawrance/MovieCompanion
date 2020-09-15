package com.accellion.moviecompanion.ui.main;

import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.accellion.moviecompanion.R;
import com.accellion.moviecompanion.model.MovieListResponseTemp;
import com.accellion.moviecompanion.model.response.MovieListResponse;
import com.accellion.moviecompanion.network.NetworkApiService;
import com.accellion.moviecompanion.network.RetrofitInstance;
import com.accellion.moviecompanion.ui.adapter.MovieListAdapter;
import com.accellion.moviecompanion.ui.adapter.PaginationListener;
import com.accellion.moviecompanion.util.Constants;
import com.accellion.moviecompanion.util.Utility;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MovieSearchFragment extends Fragment {

    private MovieSearchViewModel mViewModel;

    @BindView(R.id.listViewMovies)
    RecyclerView listViewMovies;

    MovieListAdapter movieListAdapter;
    private LinearLayoutManager layoutManager;
    private int currentPage = Constants.START_INDEX;
    private boolean isLoading = false;
    private boolean isLastPage = false;
    private int TOTAL_PAGES = 1;
    private String searchKey;

    public MovieSearchFragment newInstance() {
        MovieSearchFragment movieSearchFragment = new MovieSearchFragment();
        Bundle bundle = new Bundle();
        bundle.putString("key", "key");
        movieSearchFragment.setArguments(bundle);
        return movieSearchFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.movie_search_fragment, container, false);
        ButterKnife.bind(this, view);
        //getMovieListResponse(currentPage);
        return view;
    }

    private void getMovieListResponse(int pageIndex) {
        NetworkApiService service = RetrofitInstance.getRetrofitInstance().create(NetworkApiService.class);
        Call<MovieListResponse> call = service.getMovieList();
        call.enqueue(new Callback<MovieListResponse>() {
            @Override
            public void onResponse(Call<MovieListResponse> call, Response<MovieListResponse> response) {
                isLoading = false;
                generateDataList(response.body());
                if (currentPage == TOTAL_PAGES)
                    isLastPage = true;
            }

            @Override
            public void onFailure(Call<MovieListResponse> call, Throwable t) {
                Log.e("Error", t.getMessage());
            }
        });
    }

    private void generateDataList(MovieListResponse body) {
        if(body == null)
            return; //TODO: Display Error UI
        TOTAL_PAGES = body.getTotalPages();
        layoutManager = new LinearLayoutManager(getActivity().getApplicationContext(), LinearLayoutManager.VERTICAL, false);
        if(movieListAdapter == null) {
            movieListAdapter = new MovieListAdapter(getActivity().getApplicationContext(), body);
            listViewMovies.setAdapter(movieListAdapter);
            listViewMovies.setLayoutManager(layoutManager);
        } else {
            movieListAdapter.updateMovieList(body);
        }

        listViewMovies.addOnScrollListener(new PaginationListener(layoutManager) {
            @Override
            protected void loadMoreItems() {
                currentPage += 1;
                isLoading = true;

                Log.d("LoadMore, NextPage: ", "" + currentPage);
                getMovieListResponse(currentPage);
            }

            @Override
            public int getTotalPageCount() {
                return TOTAL_PAGES;
            }

            @Override
            public boolean isLastPage() {
                return isLastPage;
            }

            @Override
            public boolean isLoading() {
                return isLoading;
            }
        });

    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = ViewModelProviders.of(this).get(MovieSearchViewModel.class);

    }

    private MovieListResponse getMovieResponseFromAsset() {
        String response = Utility.loadDataFromAssets(getActivity().getApplicationContext(), "movie_list.json");
        MovieListResponse movieListResponseTemp = new Gson().fromJson(response, MovieListResponse.class);

        return movieListResponseTemp;
    }

    public void searchMovie(String currentQuery) {
        this.searchKey = currentQuery;
        getMovieListResponse(currentPage);
    }
}