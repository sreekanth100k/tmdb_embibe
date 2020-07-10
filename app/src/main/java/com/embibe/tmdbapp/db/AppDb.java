package com.embibe.tmdbapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

//@TypeConverters(Converters.class)
@Database(entities = {BookMarkedPhotos.class}, version = 1,exportSchema = false)
public abstract class AppDb extends RoomDatabase  {

    private static AppDb INSTANCE;

    public abstract BookMarkedMoviesDAO BookMarkedPhotosMappingDAO();

    public static AppDb getInMemoryDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.inMemoryDatabaseBuilder(context.getApplicationContext(), AppDb.class).allowMainThreadQueries().build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }
}