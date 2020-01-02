package com.zazsona.todo.model.repository;

import android.content.Context;

import androidx.lifecycle.LiveData;
import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.model.database.TodoDAO;
import com.zazsona.todo.model.database.TodoDatabase;
import java.util.List;
import java.util.UUID;

public class TodoRepository
{
    /**
     * The Data-Access-Object instance.
     */
    private TodoDAO todoDAO;

    public TodoRepository(Context context)
    {
        TodoDatabase todoDatabase = TodoDatabase.getInstance(context);
        todoDAO = todoDatabase.todoDAO();
    }

    /**
     * Gets an unmodifiable list of all saved {@link Todo}s
     * @return the todos
     */
    public LiveData<List<Todo>> getTodos()
    {
        return todoDAO.getTodos();
    }

    /**
     * Gets a {@link Todo} defined by the ID.
     * @param id the id of the Todo
     * @return the todo
     */
    public LiveData<Todo> getTodo(UUID id)
    {
        return todoDAO.getTodoById(id);
    }

    /**
     * Adds a {@link Todo} to the {@link TodoDatabase}
     * @param id a unique ID for the Todo
     * @param name the todo title
     * @param description the todo detail
     * @param complete boolean on complete.
     */
    public void addTodo(UUID id, String name, String description, boolean complete)
    {
        todoDAO.addTodo(new Todo(id, name, description, (System.currentTimeMillis()/1000), complete)); //Single point of truth for adding Todos. Set time here.
    }

    /**
     * Removes the {@link Todo} defined by the ID from the {@link TodoDatabase}
     * @param id the UUID for the Todo
     */
    public void removeTodo(UUID id)
    {
        todoDAO.removeTodo(id);
    }

    /**
     * Updates the {@link Todo} defined in the {@link TodoDatabase}
     * @param todo the Todo to update
     */
    public void updateTodo(Todo todo)
    {
        todoDAO.updateTodo(todo);
    }


}
