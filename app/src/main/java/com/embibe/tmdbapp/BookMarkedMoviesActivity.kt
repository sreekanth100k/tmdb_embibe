package com.embibe.tmdbapp

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.embibe.tmdbapp.service.models.Movie
import com.embibe.tmdbapp.viewmodel.ItemViewModel
import com.embibe.tmdbapp.viewmodel.ItemViewModelSearch
import kotlinx.android.synthetic.main.activity_main.*

class BookMarkedMoviesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bookmarked_movies)

        recyclerView = findViewById(R.id.id_rv)









    }
}