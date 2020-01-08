package com.zazsona.todo.model.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Todo.class}, version = 1)
public abstract class TodoDatabase extends RoomDatabase
{
    private static TodoDatabase databaseInstance;

    /**
     * Gets the database instance
     * @param context the application context
     * @return the database
     */
    public static TodoDatabase getInstance(Context context)
    {
        if (databaseInstance == null)
        {
            databaseInstance = Room.databaseBuilder(context.getApplicationContext(), TodoDatabase.class, "Todo-Database").build();
        }
        return databaseInstance;
    }

    /**
     * Gets the Data Access Object for the database.
     * @return the DAO
     */
    public abstract TodoDAO todoDAO();
}
