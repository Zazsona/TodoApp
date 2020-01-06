package com.zazsona.todo.model.repository;

import android.content.Context;
import android.os.AsyncTask;

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
    public LiveData<Todo> getTodo(String id)
    {
        return todoDAO.getTodoById(id);
    }

    /**
     * Adds a {@link Todo} to the {@link TodoDatabase}
     * @param name the todo title
     * @param description the todo detail
     * @param complete boolean on complete.
     */
    public void addTodo(final String name, final String description, final boolean complete)
    {
        Todo todo = new Todo(UUID.randomUUID().toString(), name, description, (System.currentTimeMillis() / 1000), complete); //Single point of truth for adding Todos. Set time here.
        new DatabaseAsyncTask(todoDAO, true, false, false).execute(todo);
    }

    /**
     * Adds a {@link Todo} to the {@link TodoDatabase}
     * @param todo the Todo to add
     */
    public void addTodo(Todo todo)
    {
        new DatabaseAsyncTask(todoDAO, true, false, false).execute(todo);
    }

    /**
     * Removes the {@link Todo} defined by the ID from the {@link TodoDatabase}
     * @param id the UUID for the Todo
     */
    public void removeTodo(String id)
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

    private static class DatabaseAsyncTask extends AsyncTask<Todo, Void, Void>
    {
        private TodoDAO todoDAO;
        private boolean insert;
        private boolean remove;
        private boolean update;

        public DatabaseAsyncTask(TodoDAO todoDAO, boolean insert, boolean remove, boolean update)
        {
            this.todoDAO = todoDAO;
            this.insert = insert;
            this.remove = remove;
            this.update = update;
        }

        @Override
        protected Void doInBackground(final Todo... todos)
        {
            for (Todo todo : todos)
            {
                if (insert)
                {
                    todoDAO.addTodo(todo);
                }
                if (update)
                {
                    todoDAO.updateTodo(todo);
                }
                if (remove)
                {
                    todoDAO.removeTodo(todo.getId());
                }
            }
            return null;
        }

    }
}
