package com.zazsona.todo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.content.Intent;
import android.os.Bundle;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;

public class ListActivity extends AppCompatActivity implements ListFragment.ListFragmentListener
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        FragmentManager fragmentManager = getSupportFragmentManager();
        ListFragment vListFragment = new ListFragment();
        fragmentManager.beginTransaction().add(R.id.rootLayout, vListFragment).commit();
    }

    /**
     * Returns an intent to open a {@link TodoDetailActivity} with the specified {@link Todo}
     * @param todo the todo
     */
    @Override
    public void openTodo(Todo todo)
    {
        Intent intent = TodoDetailActivity.newIntent(this, todo);
        startActivity(intent);
    }
}
