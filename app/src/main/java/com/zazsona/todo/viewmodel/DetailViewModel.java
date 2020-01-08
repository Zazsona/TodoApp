package com.zazsona.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.model.repository.TodoRepository;

import java.util.List;

public class DetailViewModel extends AndroidViewModel
{
    private TodoRepository todoRepository;

    public DetailViewModel(@NonNull Application application)
    {
        super(application);
        todoRepository = new TodoRepository(application);
    }

    /**
     * Gets a list of all Todos
     * @return the Todos
     */
    public LiveData<List<Todo>> getTodos()
    {
        return todoRepository.getTodos();
    }

    /**
     * Creates a new Todo
     * @param name the todo name
     * @param description the todo description
     * @param complete whether the todo is complete
     */
    public void addTodo(String name, String description, boolean complete)
    {
        todoRepository.addTodo(name, description, complete);
    }

    /**
     * Updates the Todo in the database
     * @param todo the todo to update
     */
    public void updateTodo(Todo todo)
    {
        todoRepository.updateTodo(todo);
    }

    /**
     * Removes the Todo specified from the database
     * @param todo the Todo to remove
     */
    public void deleteTodo(Todo todo)
    {
        todoRepository.removeTodo(todo);
    }

}
