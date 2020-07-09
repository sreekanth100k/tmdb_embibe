package com.embibe.tmdbapp.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;
import androidx.paging.LivePagedListBuilder;
import androidx.paging.PageKeyedDataSource;
import androidx.paging.PagedList;


import com.embibe.tmdbapp.ItemDataSourceFactorySearch;
import com.embibe.tmdbapp.ItemDataSourceSearch;
import com.embibe.tmdbapp.service.models.Movie;

public class ItemViewModelSearch extends ViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<PagedList<Movie>> itemPagedList;
    LiveData<PageKeyedDataSource<Integer, Movie>> liveDataSource;

    //constructor
    public ItemViewModelSearch(String iSearchString) {
        //getting our data source factory
        ItemDataSourceFactorySearch itemDataSourceFactorySearch =   new ItemDataSourceFactorySearch();

        liveDataSource                                          =   itemDataSourceFactorySearch.getItemLiveDataSource();

        PagedList.Config pagedListConfig =
                (new PagedList.Config.Builder())
                        .setEnablePlaceholders(false)
                        .setPageSize(ItemDataSourceSearch.MAX_PAGE_SIZE).build();

        itemPagedList = (new LivePagedListBuilder(itemDataSourceFactorySearch, pagedListConfig)).build();
    }
}
