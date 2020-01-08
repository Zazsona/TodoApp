package com.zazsona.todo.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.model.repository.TodoRepository;

import java.util.List;

public class ListViewModel extends AndroidViewModel
{
    private TodoRepository todoRepository;

    public ListViewModel(@NonNull Application application)
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

}
