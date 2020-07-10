package com.embibe.tmdbapp.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.embibe.tmdbapp.service.models.Movie;

@Dao
interface BookMarkedMoviesDAO {
    // Method to insert the answers fetched from api to room
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertResponse(bookMarkedPhotoObj:BookMarkedPhotos)

    // Method to fetch the answers stored locally
    @Query("SELECT * FROM `BookMarkedPhotos`")
    fun getListOfBookMarkedMovies(): List<Movie>


}
