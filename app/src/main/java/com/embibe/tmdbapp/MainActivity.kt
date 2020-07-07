package com.embibe.tmdbapp

import android.database.Observable
import android.os.Bundle
import androidx.annotation.Nullable
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.embibe.tmdbapp.service.models.Movie
import com.embibe.tmdbapp.viewmodel.MovieListViewModel

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val viewModel: MovieListViewModel = ViewModelProviders.of(this).get<MovieListViewModel>(
            MovieListViewModel::class.java
        )

        observeViewModel(viewModel)


    }

    private fun observeViewModel(viewModel: MovieListViewModel) {
        // Update the list when the data changes
        viewModel.movieListObservable.observe(this, Observer {

        })
    }


}