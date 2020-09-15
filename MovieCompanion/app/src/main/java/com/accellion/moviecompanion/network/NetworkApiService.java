package com.accellion.moviecompanion.network;

import com.accellion.moviecompanion.model.response.MovieListResponse;
import com.accellion.moviecompanion.util.Constants;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NetworkApiService {

    @GET("search/movie?api_key=" + Constants.API_KEY+ "&query=batman&page=1")
    Call<MovieListResponse> getMovieList();
}
