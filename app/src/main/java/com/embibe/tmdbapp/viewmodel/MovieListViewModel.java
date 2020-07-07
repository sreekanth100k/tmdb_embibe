package com.embibe.tmdbapp.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.embibe.tmdbapp.service.models.Movie;
import com.embibe.tmdbapp.service.models.Response;
import com.embibe.tmdbapp.service.repository.ProjectRepository;

import java.util.List;

public class MovieListViewModel extends AndroidViewModel {
    private final LiveData<Response> movieListObservable;
    private String apiKey = "15876f79b1e52133351b6055744a28cd";

    public MovieListViewModel(Application application){
        super(application);

        movieListObservable = ProjectRepository.INSTANCE.getNowPlayingList(apiKey,String.valueOf(1));
    }

    /**
     * Expose the LiveData Projects query so the UI can observe it.
     */
    public LiveData<Response> getMovieListObservable() {
        return movieListObservable;
    }
}
