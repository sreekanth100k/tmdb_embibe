package com.embibe.tmdbapp

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.RecyclerView
import com.embibe.tmdbapp.db.BookMarkedMovie
import com.embibe.tmdbapp.viewmodel.ItemViewModelBookMarked


class BookMarkedMoviesActivity : AppCompatActivity() {
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_bookmarked_movies)

        recyclerView    =   findViewById(R.id.id_rv)

        val itemViewModel: ItemViewModelBookMarked = ViewModelProviders.of(this).get<ItemViewModelBookMarked>(ItemViewModelBookMarked::class.java)

        var movieAdapterBookMarked:MovieAdapterBookmarked = MovieAdapterBookmarked (this,ArrayList<BookMarkedMovie>());

        itemViewModel.itemArrayList.observe(this, Observer {
            var movieList:java.util.ArrayList<BookMarkedMovie>  = java.util.ArrayList<BookMarkedMovie>()
            var movieAdapterBookMarkedNew:MovieAdapterBookmarked   = MovieAdapterBookmarked (this,movieList);

            recyclerView.adapter = movieAdapterBookMarkedNew

            movieAdapterBookMarkedNew.notifyDataSetChanged()
        })

        recyclerView.adapter = movieAdapterBookMarked
    }
}