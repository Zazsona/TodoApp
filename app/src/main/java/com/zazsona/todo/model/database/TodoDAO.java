package com.zazsona.todo.model.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TodoDAO
{
    @Query("SELECT * FROM todos")
    LiveData<List<Todo>> getTodos();

    @Query("SELECT * FROM todos WHERE id = :id")
    LiveData<Todo> getTodoById(String id);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void addTodo(Todo todo);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    void updateTodo(Todo todo);

    @Query("DELETE FROM todos WHERE id = :id")
    void removeTodo(String id);
}
