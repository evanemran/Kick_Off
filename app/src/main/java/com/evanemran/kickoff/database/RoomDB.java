package com.evanemran.kickoff.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.evanemran.kickoff.models.MatchDataFly;

@Database(entities = {MatchDataFly.class}, version = 1, exportSchema = false)
public abstract class RoomDB extends RoomDatabase {
    private static RoomDB database;
    private static String DATABASE_NAME = "KickOff";

    public synchronized static RoomDB getInstance(Context context){
        if (database == null){
            database = Room.databaseBuilder(context.getApplicationContext(),
                            RoomDB.class, DATABASE_NAME)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return database;
    }

    public abstract MainDAO mainDAO();
}
