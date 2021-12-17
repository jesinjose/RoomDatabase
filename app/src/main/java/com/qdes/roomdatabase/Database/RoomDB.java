package com.qdes.roomdatabase.Database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.qdes.roomdatabase.Model.UserData;

@Database(entities = {UserData.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {

    private static RoomDB database;
    private static String DATABSE_NAME = "database";


    public synchronized static RoomDB getInstance(Context context) {

        if (database == null) {

            database = Room.databaseBuilder(context.getApplicationContext()
                    , RoomDB.class, DATABSE_NAME).allowMainThreadQueries().fallbackToDestructiveMigration().build();

        }

        return database;
    }

    public abstract MainDao mainDao();
}
