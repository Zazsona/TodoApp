package com.zazsona.todo.model.database;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;
import java.util.UUID;

@Entity(tableName = "todos")
public class Todo implements Serializable
{
    @PrimaryKey
    @NonNull
    private final String id;
    private final String name;
    private final String description;
    private final long secondsSetEpoch;
    private boolean complete;

    public Todo(@NonNull String id, String name, String description, long secondsSetEpoch, boolean complete)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.secondsSetEpoch = secondsSetEpoch;
        this.complete = complete;
    }

    /**
     * Gets id
     * @return id
     */
    public String getId()
    {
        return id;
    }

    /**
     * Gets name
     * @return name string
     */
    public String getName()
    {
        return name;
    }

    /**
     * Gets description
     * @return description string
     */
    public String getDescription()
    {
        return description;
    }

    /**
     * Gets secondsSetEpoch
     * @return secondsSetEpoch
     */
    public long getSecondsSetEpoch()
    {
        return secondsSetEpoch;
    }

    /**
     * Gets complete
     * @return complete
     */
    public boolean isComplete()
    {
        return complete;
    }
}
