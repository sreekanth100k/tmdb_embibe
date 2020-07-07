package com.embibe.tmdbapp.service.repository;

import retrofit2.http.GET;
import retrofit2.http.Path;

public interface TmdbService {
    String HTTPS_API_TMDB_URL = "https://api.themoviedb.org/3/";

    @GET("movie/now_playing?api_key={api_key}&page={page}")
    void getNowPlayingList(@Path("api_key") String apiKey, @Path("page") String page);

    @GET("search/movie?api_key=xxx&query=xxx&page=xxx")
    void searchMovie(@Path("api_key") String api_key, @Path("query") String query, @Path("page")Integer page);

}
