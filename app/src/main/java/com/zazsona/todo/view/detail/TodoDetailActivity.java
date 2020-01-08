package com.zazsona.todo.view;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;

public class TodoDetailActivity extends AppCompatActivity implements TodoDetailFragment.TodoDetailFragmentListener
{
    private static String TODO_PARAM = "com.zazsona.todo.view.tododetailactivity.todo";
    private Todo mTodo;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);

        mTodo = (Todo) getIntent().getSerializableExtra(TODO_PARAM);
        FragmentManager fragmentManager = getSupportFragmentManager();
        fragmentManager.beginTransaction().add(R.id.detailRootLayout, TodoDetailFragment.newInstance(mTodo)).commit();
    }

    /**
     * Returns an intent for this Activity
     * @param context the application context
     * @param todo the todo to load
     * @return the intent
     */
    public static Intent newIntent(Context context, Todo todo)
    {
        Intent intent = new Intent(context, TodoDetailActivity.class);
        intent.putExtra(TODO_PARAM, todo);
        return intent;
    }
}
