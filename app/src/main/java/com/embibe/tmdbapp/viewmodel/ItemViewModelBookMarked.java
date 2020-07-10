package com.embibe.tmdbapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;

import com.embibe.tmdbapp.ItemDataSource;
import com.embibe.tmdbapp.ItemDataSourceFactory;
import com.embibe.tmdbapp.db.BookMarkedMovie;
import com.embibe.tmdbapp.service.models.Movie;

import java.util.ArrayList;

public class ItemViewModelBookMarked extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<ArrayList<BookMarkedMovie>> itemArrayList;
    LiveData<PageKeyedDataSource<Integer, BookMarkedMovie>> liveDataSource;

    //constructor
    public ItemViewModelBookMarked() {
        //getting our data source factory
        ItemDataSourceFactory itemDataSourceFactory = new ItemDataSourceFactory();

        //getting the live data source from data source factory

        //Getting PagedList config
        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSource.MAX_PAGE_SIZE).build();

    }
}
