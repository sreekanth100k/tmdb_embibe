package com.embibe.tmdbapp

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.paging.PagedList
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.embibe.tmdbapp.service.models.Movie
import com.embibe.tmdbapp.viewmodel.ItemViewModel
import com.embibe.tmdbapp.viewmodel.ItemViewModelSearch
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    lateinit var recyclerView:RecyclerView
    private val apiKey = "15876f79b1e52133351b6055744a28cd"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        //setting up recyclerview
        recyclerView = findViewById(R.id.id_rv);
        recyclerView.setLayoutManager(LinearLayoutManager (this));
        recyclerView.setHasFixedSize(true);

        //getting our ItemViewModel
        val itemViewModel: ItemViewModel = ViewModelProviders.of(this).get<ItemViewModel>(ItemViewModel::class.java)

        //creating the Adapter
        var movieAdapter:MovieAdapter = MovieAdapter (this);

        itemViewModel.itemPagedList.observe(this, Observer <PagedList<Movie>>  () {
            //in case of any changes
            //submitting the items to adapter
            var pagedList:PagedList<Movie> = it

            movieAdapter.submitList(pagedList);


        } )

        recyclerView.adapter = movieAdapter

        id_search_by_keyword_et.doAfterTextChanged {
            var searchKeyWord:String =  it.toString()

            this.getViewModelStore().clear();

            if(searchKeyWord.equals("")) {


                //getting our ItemViewModel
                val itemViewModel: ItemViewModel = ViewModelProviders.of(this).get<ItemViewModel>(ItemViewModel::class.java)

                //creating the Adapter
                var movieAdapter:MovieAdapter = MovieAdapter (this);

                itemViewModel.itemPagedList.observe(this, Observer <PagedList<Movie>>  () {
                    //in case of any changes
                    //submitting the items to adapter
                    var pagedList:PagedList<Movie> = it

                    movieAdapter.submitList(pagedList);


                } )

                recyclerView.adapter = movieAdapter



            }else{


                val itemViewModelSearch: ItemViewModelSearch = ViewModelProvider(
                    this,
                    MyViewModelFactory(this.application, searchKeyWord)
                ).get(
                    ItemViewModelSearch::class.java
                )


                //creating the Adapter
                var movieAdapter: MovieAdapter = MovieAdapter(this);

                itemViewModelSearch.itemPagedList.observe(this, Observer<PagedList<Movie>>() {
                    //in case of any changes
                    //submitting the items to adapter
                    var pagedList: PagedList<Movie> = it

                    movieAdapter.submitList(pagedList);

                })

                recyclerView.adapter = movieAdapter


            }
        }

        id_view_bookmarked_btn.setOnClickListener(View.OnClickListener {

        })
    }
}