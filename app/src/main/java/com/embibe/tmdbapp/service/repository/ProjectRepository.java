package com.embibe.tmdbapp.service.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.embibe.tmdbapp.service.NetworkService;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectRepository {
    public static  ProjectRepository INSTANCE ;

    private ProjectRepository() {
        INSTANCE = (ProjectRepository) this;
    }

    static {
        new ProjectRepository();
    }

    public LiveData<com.embibe.tmdbapp.service.models.Response> getNowPlayingList(String iApiKey, String iPage) {

        final MutableLiveData<com.embibe.tmdbapp.service.models.Response> data = new MutableLiveData<>();

        NetworkService networkServiceObj = NetworkService.getInstance();

        networkServiceObj.getJSONApi().getNowPlayingList(iApiKey,iPage).enqueue(new Callback<com.embibe.tmdbapp.service.models.Response>() {
            @Override
            public void onResponse(Call<com.embibe.tmdbapp.service.models.Response> call, Response<com.embibe.tmdbapp.service.models.Response> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<com.embibe.tmdbapp.service.models.Response> call, Throwable t) {
                Log.d("ProjectRepository",t.getLocalizedMessage());

            }
        });

        return data;

    }
}
