package com.zazsona.todo.model.database;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.UUID;

@Entity(tableName = "todos")
public class Todo
{
    @PrimaryKey
    private final UUID id;
    private final String name;
    private final String description;
    private final long secondsSetEpoch;
    private boolean complete;

    public Todo(UUID id, String name, String description, long secondsSetEpoch, boolean complete)
    {
        this.id = id;
        this.name = name;
        this.description = description;
        this.secondsSetEpoch = secondsSetEpoch;
        this.complete = complete;
    }

    /**
     * Gets id
     * @return id UUID
     */
    public UUID getID()
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
