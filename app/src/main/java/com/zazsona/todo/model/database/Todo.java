package com.zazsona.todo.model.database;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "todos")
public class Todo implements Serializable
{
    @PrimaryKey
    @NonNull
    private final String id;
    private String name;
    private String description;
    private long secondsSetEpoch;
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
    @NonNull
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

    /**
     * Sets the value of name
     *
     * @param name the value to set
     */
    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * Sets the value of description
     *
     * @param description the value to set
     */
    public void setDescription(String description)
    {
        this.description = description;
    }

    /**
     * Sets the value of secondsSetEpoch
     *
     * @param secondsSetEpoch the value to set
     */
    public void setSecondsSetEpoch(long secondsSetEpoch)
    {
        this.secondsSetEpoch = secondsSetEpoch;
    }

    /**
     * Sets the value of complete
     *
     * @param complete the value to set
     */
    public void setComplete(boolean complete)
    {
        this.complete = complete;
    }
}
