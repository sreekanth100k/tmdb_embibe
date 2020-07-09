package com.embibe.tmdbapp;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.embibe.tmdbapp.service.models.Movie;

public class ItemDataSourceFactorySearch extends DataSource.Factory {

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Movie>> itemLiveDataSource = new MutableLiveData<>();


    @Override
    public DataSource<Integer, Movie> create() {
        //getting our data source object
        ItemDataSource itemDataSource = new ItemDataSource();

        //posting the datasource to get the values
        itemLiveDataSource.postValue(itemDataSource);

        //returning the datasource
        return itemDataSource;
    }


    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Movie>> getItemLiveDataSource() {
        return itemLiveDataSource;
    }
}