package com.embibe.tmdbapp;

import androidx.annotation.NonNull;
import androidx.paging.PageKeyedDataSource;

import com.embibe.tmdbapp.service.NetworkService;
import com.embibe.tmdbapp.service.models.Movie;
import com.embibe.tmdbapp.service.models.Response;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;

public class ItemDataSourceSearch extends PageKeyedDataSource<Integer, Movie> {

    public ItemDataSourceSearch(String iSearchString){
        searchParam = iSearchString;
    }

    //the size of a page that we want
    public static final int MAX_PAGE_SIZE = 21;

    //we will start from the first page which is 1
    private static final int FIRST_PAGE = 1;

    private static String apiKey  = "15876f79b1e52133351b6055744a28cd";
    private String searchParam;

    //this will be called once to load the initial data
    @Override
    public void loadInitial(@NonNull LoadInitialParams<Integer> params, @NonNull final LoadInitialCallback<Integer, Movie> callback) {
        NetworkService.getInstance().getJSONApi().searchMovie(apiKey,searchParam,String.valueOf(FIRST_PAGE)).enqueue(new Callback<Response>() {
            @Override
            public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                if (response.body() != null) {
                    List<Movie> movieList =  response.body().getResults();
                    callback.onResult(movieList, null, FIRST_PAGE+1);
                }
            }

            @Override
            public void onFailure(Call<Response> call, Throwable t) {

            }
        });
    }



    //this will load the previous page
    @Override
    public void loadBefore(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        NetworkService.getInstance()
                .getJSONApi().searchMovie(apiKey,searchParam,String.valueOf(MAX_PAGE_SIZE))
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        //if the current page is greater than one
                        //we are decrementing the page number
                        //else there is no previous page
                        Integer adjacentKey = (params.key > 1) ? params.key - 1 : null;

                        if (response.body() != null) {

                            //passing the loaded data
                            //and the previous page key
                            List<Movie> movieList =  response.body().getResults();
                            callback.onResult(movieList, adjacentKey);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }

    //this will load the next page
    @Override
    public void loadAfter(@NonNull final LoadParams<Integer> params, @NonNull final LoadCallback<Integer, Movie> callback) {
        NetworkService.getInstance()
                .getJSONApi().searchMovie(apiKey,searchParam,String.valueOf(params.key))
                .enqueue(new Callback<Response>() {
                    @Override
                    public void onResponse(Call<Response> call, retrofit2.Response<Response> response) {
                        if (response.body() != null) {
                            //if the response has next page
                            //incrementing the next page number
                            Integer key;

                            if(response.body().getTotal_pages()>params.key){
                                key = params.key+1;
                            }else{
                                key = null;
                            }

                            List<Movie> movieList = response.body().getResults();
                            //passing the loaded data and next page value
                            callback.onResult(movieList, key);
                        }
                    }

                    @Override
                    public void onFailure(Call<Response> call, Throwable t) {

                    }
                });
    }
}

