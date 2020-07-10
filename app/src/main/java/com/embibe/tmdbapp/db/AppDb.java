package com.embibe.tmdbapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@TypeConverters(Converters.class)
@Database(entities = {BookMarkedMovie.class}, version = 1,exportSchema = true)
public abstract class AppDb extends RoomDatabase  {

    private static AppDb INSTANCE;

    public abstract BookMarkedMoviesDAO BookMarkedPhotosMappingDAO();

    public static AppDb getDbInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDb.class,"my_sample_app").allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}