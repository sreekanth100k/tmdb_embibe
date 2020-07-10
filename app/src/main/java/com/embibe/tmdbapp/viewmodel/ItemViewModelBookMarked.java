package com.embibe.tmdbapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.embibe.tmdbapp.db.AppDb;
import com.embibe.tmdbapp.db.BookMarkedMovie;
import com.embibe.tmdbapp.db.BookMarkedMoviesDAO;
import com.embibe.tmdbapp.db.BookMarkedMoviesDAOCopy;

import java.util.List;

public class ItemViewModelBookMarked extends AndroidViewModel {

    //creating livedata for PagedList  and PagedKeyedDataSource
    public LiveData<List<BookMarkedMovie>> mItemArrayList;
    private BookMarkedMoviesDAOCopy mBookMarkedMoviesDao;

    public ItemViewModelBookMarked(Application application) {
        super(application);

        mBookMarkedMoviesDao    =   AppDb.getDbInstance(application).bookMarkedMoviesMappingCopyDAO();
        mItemArrayList          =   mBookMarkedMoviesDao.getListOfBookMarkedMovies();
    }

    // Use LiveData for getting all the data from the database
    public LiveData<List<BookMarkedMovie>> getMovies() {
        return mItemArrayList;
    }

}
