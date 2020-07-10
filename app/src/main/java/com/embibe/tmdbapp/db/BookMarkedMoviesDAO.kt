package com.embibe.tmdbapp.db;

import androidx.lifecycle.LiveData
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.embibe.tmdbapp.service.models.Movie;

@Dao
interface BookMarkedMoviesDAO {
    // Method to insert the answers fetched from api to room
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(bookMarkedPhotoObj:BookMarkedMovie)

    // Method to fetch the answers stored locally
    @Query("SELECT * FROM BookMarkedMovie")
    fun getListOfBookMarkedMovies(): LiveData<List<BookMarkedMovie>>

    @Query("DELETE FROM BookMarkedMovie WHERE id = :id")
    fun removeBookMarkedMovieWithId(id:String)


}
