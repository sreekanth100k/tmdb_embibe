package com.embibe.tmdbapp;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;

import com.embibe.tmdbapp.viewmodel.ItemViewModel;
import com.embibe.tmdbapp.viewmodel.ItemViewModelSearch;

public class MyViewModelFactory implements ViewModelProvider.Factory {

    private Application mApplication;
    private String mParam;

    public MyViewModelFactory(Application application, String param) {
        mApplication = application;
        mParam = param;
    }
    @NonNull
    @Override
    public <T extends ViewModel> T create(@NonNull Class<T> modelClass) {
        return (T) new ItemViewModelSearch(mParam);

    }
}
