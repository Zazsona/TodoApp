package com.zazsona.todo.view.list;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.view.detail.TodoDetailActivity;

public class ListActivity extends AppCompatActivity implements ListFragment.ListFragmentListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        if (fragmentManager.findFragmentById(R.id.rootLayout) == null)
        {
            ListFragment vListFragment = new ListFragment();
            fragmentManager.beginTransaction().add(R.id.rootLayout, vListFragment).commit();
        }
    }

    /**
     * Launches an intent to open a {@link TodoDetailActivity} with the specified {@link Todo}
     * @param todoIndex the todo
     */
    @Override
    public void openTodo(int todoIndex)
    {
        Intent intent = TodoDetailActivity.newIntent(this, todoIndex);
        startActivity(intent);
    }

    /**
     * Launches an intent to open a {@link TodoDetailActivity} with a new Todo
     */
    @Override
    public void addTodo()
    {
        Intent intent = TodoDetailActivity.newIntent(this, -1);
        startActivity(intent);
    }
}
