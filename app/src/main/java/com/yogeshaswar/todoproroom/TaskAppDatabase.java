package com.yogeshaswar.todoproroom;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import java.net.ContentHandler;

@Database(entities = Task.class,exportSchema = false, version = 1)
public abstract class TaskAppDatabase extends RoomDatabase {
    public abstract TaskDao taskDao();

    private static final String DATABASE_NAME = "task.db";
    private static TaskAppDatabase instance;
    public static synchronized TaskAppDatabase getDB(Context context) {
        if(instance == null) {
            instance = Room.databaseBuilder(context, TaskAppDatabase.class, DATABASE_NAME)
                    .fallbackToDestructiveMigration()
                    .allowMainThreadQueries()
                    .build();
        }

        return instance;
    }

}
