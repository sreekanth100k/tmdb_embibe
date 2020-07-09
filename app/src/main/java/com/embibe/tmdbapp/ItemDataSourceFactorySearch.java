package com.embibe.tmdbapp;


import androidx.lifecycle.MutableLiveData;
import androidx.paging.DataSource;
import androidx.paging.PageKeyedDataSource;

import com.embibe.tmdbapp.service.models.Movie;

public class ItemDataSourceFactorySearch extends DataSource.Factory<Integer, Movie> {

    public ItemDataSourceFactorySearch(String iSearchParam){
        searchParam = iSearchParam;
    }

    //creating the mutable live data
    private MutableLiveData<PageKeyedDataSource<Integer, Movie>> itemLiveDataSourceSearch = new MutableLiveData<>();

    private String searchParam;
    @Override
    public DataSource<Integer, Movie> create() {

        //getting our data source object
        ItemDataSourceSearch itemDataSourceSearch = new ItemDataSourceSearch(searchParam);

        //posting the datasource to get the values
        itemLiveDataSourceSearch.postValue(itemDataSourceSearch);

        //returning the datasource
        return itemDataSourceSearch;
    }

    //getter for itemlivedatasource
    public MutableLiveData<PageKeyedDataSource<Integer, Movie>> getItemLiveDataSource() {
        return itemLiveDataSourceSearch;
    }
}