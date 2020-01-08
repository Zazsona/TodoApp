package com.zazsona.todo.view.list;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.viewmodel.ListViewModel;

import java.util.List;

public class ListFragment extends Fragment
{
    private ListViewModel mViewModel;
    private FloatingActionButton vAddButton;
    private RecyclerView vTodosList;
    private TodoListAdapter vListAdapter;

    private ListFragmentListener mListener;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        mViewModel = new ViewModelProvider(this).get(ListViewModel.class);
        View vView = inflater.inflate(R.layout.fragment_list, container, false);
        vAddButton = vView.findViewById(R.id.btnAddTodo);
        vTodosList = vView.findViewById(R.id.ryclrTodos);
        vTodosList.setLayoutManager(new LinearLayoutManager(this.getContext()));

        vListAdapter = new TodoListAdapter(this.getContext());
        mViewModel.getTodos().observe(this.getViewLifecycleOwner(), new Observer<List<Todo>>()
        {
            @Override
            public void onChanged(List<Todo> todos)
            {
                vListAdapter.setTodos(todos);
                vTodosList.setAdapter(vListAdapter);        //Update the RecylerList when the database changes
            }
        });
        vAddButton.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                mListener.addTodo();
            }
        });
        return vView;
    }

    @Override
    public void onAttach(@NonNull Context context)
    {
        super.onAttach(context);
        if (context instanceof ListFragmentListener)
        {
            mListener = (ListFragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement ListFragmentListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface ListFragmentListener
    {
        void openTodo(int todoIndex);

        void addTodo();
    }
}
