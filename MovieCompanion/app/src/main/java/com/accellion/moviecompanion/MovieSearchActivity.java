package com.accellion.moviecompanion;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;
import androidx.appcompat.widget.Toolbar;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcel;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;

import com.accellion.moviecompanion.ui.main.MovieSearchFragment;
import com.arlib.floatingsearchview.FloatingSearchView;
import com.arlib.floatingsearchview.suggestions.model.SearchSuggestion;

import java.util.ArrayList;
import java.util.List;

public class MovieSearchActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    public static final long FIND_SUGGESTION_SIMULATED_DELAY = 250;
    private FloatingSearchView mSearchView;
    final List<SearchSuggestion> suggestionList = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_search_activity);
        MovieSearchFragment movieSearchFragment = new MovieSearchFragment();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.container, movieSearchFragment.newInstance())
                    .commitNow();
        }
        mSearchView = (FloatingSearchView) findViewById(R.id.floating_search_view);
        mSearchView.setOnSearchListener(new FloatingSearchView.OnSearchListener() {
            @Override
            public void onSuggestionClicked(SearchSuggestion searchSuggestion) {
                movieSearchFragment.searchMovie(searchSuggestion.getBody());
            }

            @Override
            public void onSearchAction(String currentQuery) {
                movieSearchFragment.searchMovie(currentQuery);
            }
        });

        mSearchView.setOnFocusChangeListener(new FloatingSearchView.OnFocusChangeListener() {
            @Override
            public void onFocus() {
                Log.d("", "");
                DataHelper.findSuggestions(MovieSearchActivity.this, "", 5,
                        FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                            @Override
                            public void onResults(List<MovieSuggestion> results) {

                                //this will swap the data and
                                //render the collapse/expand animations as necessary
                                mSearchView.swapSuggestions(results);

                                //let the users know that the background
                                //process has completed
                                mSearchView.hideProgress();
                            }
                        });
            }

            @Override
            public void onFocusCleared() {
                Log.d("", "");
            }
        });
        mSearchView.setOnQueryChangeListener((oldQuery, newQuery) -> {

            if (!oldQuery.equals("") && newQuery.equals("")) {
                mSearchView.clearSuggestions();
            } else {
                DataHelper.findSuggestions(MovieSearchActivity.this, newQuery, 5,
                        FIND_SUGGESTION_SIMULATED_DELAY, new DataHelper.OnFindSuggestionsListener() {

                            @Override
                            public void onResults(List<MovieSuggestion> results) {

                                //this will swap the data and
                                //render the collapse/expand animations as necessary
                                mSearchView.swapSuggestions(results);

                                //let the users know that the background
                                //process has completed
                                mSearchView.hideProgress();
                            }
                        });
            }
        });

    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_search:
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}