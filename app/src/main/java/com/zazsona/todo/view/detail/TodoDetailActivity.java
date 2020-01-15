package com.zazsona.todo.view.detail;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.viewmodel.DetailViewModel;

import java.util.List;

public class TodoDetailActivity extends AppCompatActivity
{
    private static String TODO_PARAM = "com.zazsona.todo.view.tododetailactivity.todoindex";
    private int currentTodoIndex;
    private ViewPager vViewPager;
    private DetailViewModel vViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_detail);
        vViewModel = new ViewModelProvider(this).get(DetailViewModel.class);    //Get ViewModel from Factory

        if (savedInstanceState != null)
        {
            currentTodoIndex = savedInstanceState.getInt(TODO_PARAM);
        }
        else
        {
            currentTodoIndex = getIntent().getIntExtra(TODO_PARAM, -1);
        }
        vViewPager = findViewById(R.id.vpgrTodoDetail);
        vViewModel.getTodos().observe(this, new Observer<List<Todo>>()
        {
            @Override
            public void onChanged(final List<Todo> todos)
            {
                if (currentTodoIndex >= 0)
                {
                    vViewPager.setVisibility(View.VISIBLE);
                    vViewPager.setAdapter(new FragmentStatePagerAdapter(getSupportFragmentManager(), FragmentStatePagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT)
                    {
                        @NonNull
                        @Override
                        public Fragment getItem(int position)
                        {
                            return TodoDetailFragment.newInstance(todos.get(position));    //Generate new Fragment state
                        }

                        @Override
                        public int getCount()
                        {
                            return todos.size();
                        }
                    });
                    vViewPager.setCurrentItem(currentTodoIndex);
                }
                else
                {
                    vViewPager.setVisibility(View.GONE);
                    FragmentManager fm = getSupportFragmentManager();
                    if (fm.findFragmentById(R.id.detailRootLayout) == null)
                    {
                        fm.beginTransaction().add(R.id.detailRootLayout, TodoDetailFragment.newInstance(null)).commit();
                        //No ViewPager here, as the user has prompted the menu via button, rather than list, and this is an add rather than edit operation. UX.
                    }
                }
            }
        });
    }

    /**
     * Returns an intent for this Activity
     * @param context the application context
     * @param currentTodoIndex the todo to load
     * @return the intent
     */
    public static Intent newIntent(Context context, int currentTodoIndex)
    {
        Intent intent = new Intent(context, TodoDetailActivity.class);
        intent.putExtra(TODO_PARAM, currentTodoIndex);  //Specify Todo to display
        return intent;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState)
    {
        super.onSaveInstanceState(outState);
        if (currentTodoIndex >= 0)
        {
            outState.putInt(TODO_PARAM, vViewPager.getCurrentItem());
        }
        else
        {
            outState.putInt(TODO_PARAM, -1);
        }

    }
}
