package com.embibe.tmdbapp.service.repository;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.embibe.tmdbapp.service.NetworkService;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    public NetworkService networkService;

    public static  ProjectRepository INSTANCE ;

    private ProjectRepository() {
        INSTANCE = (ProjectRepository) this;
    }

    static {
        new ProjectRepository();
    }

    public LiveData<List<Movie>> getNowPlayingList(String iApiKey, String iPage) {

        final MutableLiveData<List<Movie>> data = new MutableLiveData<>();

//        networkService.getJSONApi(iApiKey, iPage).enqueue(new Callback<List<Movie>>() {
//            @Override
//            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
//
//                data.setValue(response.body());
//
//            }
//
//            @Override
//            public void onFailure(Call<List<Movie>> call, Throwable t) {
//
//            }
//        });
        NetworkService networkServiceObj = NetworkService.getInstance();

        networkServiceObj.getJSONApi().getNowPlayingList(iApiKey,iPage).enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(Call<List<Movie>> call, Response<List<Movie>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Movie>> call, Throwable t) {

            }
        });

        return data;

    }
}
