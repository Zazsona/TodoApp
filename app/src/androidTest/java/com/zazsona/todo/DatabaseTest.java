package com.zazsona.todo;

import android.content.Context;
import android.util.Log;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LifecycleRegistry;
import androidx.lifecycle.Observer;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.model.repository.TodoRepository;
import com.zazsona.todo.view.list.ListActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.List;
import java.util.UUID;

import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class DatabaseTest
{
    private int originalSize = -1;
    private int newSize = -1;

    @Rule
    public InstantTaskExecutorRule executorRule = new InstantTaskExecutorRule(); //Swaps this thread for an Observer compatible one.

    public Todo createEntry()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TodoRepository todoRepository = new TodoRepository(appContext);
        String uuid = UUID.randomUUID().toString();
        Todo todo = new Todo(uuid, "foobar", "foobar desc", System.currentTimeMillis()/1000, false);
        todoRepository.addTodo(todo);
        Log.d("DEBUG", "Todo created with ID "+todo.getId());
        return todo;
    }

    @Test
    public void CreateAndRead()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TodoRepository todoRepository = new TodoRepository(appContext);
        Todo todo = createEntry();
        assertTrue(todoRepository.getTodo(todo.getId()) != null);
        Log.d("DEBUG", "Todo read with ID "+todo.getId());
    }

    @Test
    public void CreateAndRemove()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        final TodoRepository todoRepository = new TodoRepository(appContext);
        final Todo createdTodo = createEntry();
        Observer<List<Todo>> observer = new Observer<List<Todo>>()
        {
            @Override
            public void onChanged(List<Todo> todos)
            {
                if (originalSize < 0)
                {
                    originalSize = todos.size();
                    todoRepository.removeTodo(createdTodo);
                }
                else
                {
                    newSize = todos.size();
                }
            }
        };
        todoRepository.getTodos().observeForever(observer);
        while (newSize < 0)
        {
            try {Thread.sleep(1000);} catch (InterruptedException e) {};
        }
        Log.d("DEBUG", ""+originalSize);
        Log.d("DEBUG", ""+newSize);
        assertTrue(newSize < originalSize);
    }

    @Test
    public void CreateAndUpdate()
    {
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();
        TodoRepository todoRepository = new TodoRepository(appContext);
        Todo todo = createEntry();
        final boolean originalValue = todo.isComplete();
        todo.setComplete(!originalValue);
        todoRepository.updateTodo(todo);
        todoRepository.getTodo(todo.getId()).observeForever(new Observer<Todo>()
        {
            @Override
            public void onChanged(Todo todo)
            {
                assertTrue(todo.isComplete() != originalValue);
            }
        });
    }
}
