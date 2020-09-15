package com.accellion.moviecompanion.ui.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.appcompat.widget.AppCompatTextView;
import androidx.recyclerview.widget.RecyclerView;

import com.accellion.moviecompanion.R;
import com.accellion.moviecompanion.model.response.MovieListResponse;
import com.accellion.moviecompanion.model.response.ResultsItem;
import com.accellion.moviecompanion.util.Utility;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.accellion.moviecompanion.util.Constants.SOURCE_PATH;

public class MovieListAdapter extends RecyclerView.Adapter<MovieListAdapter.MovieListViewHolder> {

    private MovieListResponse movieListResponse;
    private Context mContext;

    public MovieListAdapter(Context context, MovieListResponse movieResponseList) {
        this.mContext = context;
        this.movieListResponse = movieResponseList;

    }
    @NonNull
    @Override
    public MovieListAdapter.MovieListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.movie_list_row, parent, false);
        return new MovieListAdapter.MovieListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieListAdapter.MovieListViewHolder holder, int position) {
        ResultsItem resultsItem = movieListResponse.getResults().get(position);
        holder.txtViewMovieName.setText(resultsItem.getTitle());
        holder.txtViewReleaseDate.setText(Utility.convertDateFormat(mContext, resultsItem.getReleaseDate()));
        holder.txtViewMovieDesc.setText(resultsItem.getOverview());
        Glide.with(mContext)
                .load(SOURCE_PATH + resultsItem.getPosterPath())
                .placeholder(R.drawable.error)
                .into(holder.imgViewMoviePoster);
                /*.into(new SimpleTarget<Drawable>() {
                    @Override
                    public void onResourceReady(@NonNull Drawable resource, @Nullable Transition<? super Drawable> transition) {
                        holder.imgViewMoviePoster.setImageBitmap(drawableToBitmap(resource));
                    }
                });*/


    }

    @Override
    public int getItemCount() {
        return movieListResponse.getResults().size();
    }

    public void updateMovieList(MovieListResponse body) {
        movieListResponse.getResults().addAll(body.getResults());
        notifyDataSetChanged();
    }

    public class MovieListViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.txtViewMovieName)
        AppCompatTextView txtViewMovieName;

        @BindView(R.id.imgViewMoviePoster)
        AppCompatImageView imgViewMoviePoster;

        @BindView(R.id.txtViewReleaseDate)
        AppCompatTextView txtViewReleaseDate;

        @BindView(R.id.txtViewMovieDesc)
        AppCompatTextView txtViewMovieDesc;

        MovieListViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public static Bitmap drawableToBitmap (Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable)drawable).getBitmap();
        }

        int width = drawable.getMinimumWidth();
        width = width > 0 ? width : 1;
        int height = drawable.getMinimumHeight();
        height = height > 0 ? height : 1;

        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

        return bitmap;
    }

}
