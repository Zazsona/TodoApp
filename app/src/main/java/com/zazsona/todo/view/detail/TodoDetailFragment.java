package com.zazsona.todo.view;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;
import com.zazsona.todo.viewmodel.DetailViewModel;

public class TodoDetailFragment extends Fragment
{
    private static final String TODO_PARAM = "com.zazsona.todo.view.tododetailfragment.todo";
    private Todo mTodo;

    private EditText vTitle;
    private EditText vDesc;
    private CheckBox vComplete;
    private Button vSave;
    private Button vDelete;

    private TodoDetailFragmentListener mListener;
    private DetailViewModel mViewModel;

    public TodoDetailFragment()
    {
        // Required empty public constructor
    }

    /**
     * Creates a new instance of this fragment, loading the data from the Todo
     * @param todo the Todo to load in
     * @return the Fragment
     */
    public static TodoDetailFragment newInstance(Todo todo)
    {
        TodoDetailFragment fragment = new TodoDetailFragment();
        Bundle args = new Bundle();
        args.putSerializable(TODO_PARAM, todo);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(DetailViewModel.class);
        if (getArguments() != null)
        {
            mTodo = (Todo) getArguments().get(TODO_PARAM);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View vView = inflater.inflate(R.layout.fragment_todo_detail, container, false);
        vTitle = vView.findViewById(R.id.txtTodoName);
        vDesc = vView.findViewById(R.id.txtTodoDesc);
        vComplete = vView.findViewById(R.id.chkTodoComplete);
        vSave = vView.findViewById(R.id.btnSave);
        vDelete = vView.findViewById(R.id.btnDelete);
        if (mTodo != null)
        {
            vTitle.setText(mTodo.getName());
            vDesc.setText(mTodo.getDescription());
            vComplete.setChecked(mTodo.isComplete());
        }
        vSave.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mTodo == null)
                {
                    mViewModel.addTodo(vTitle.getText().toString(), vDesc.getText().toString(), vComplete.isChecked());
                }
                else
                {
                    mTodo.setName(vTitle.getText().toString());
                    mTodo.setDescription(vDesc.getText().toString());
                    mTodo.setComplete(vComplete.isChecked());
                    mViewModel.updateTodo(mTodo);
                }
                getActivity().onBackPressed();
            }
        });
        vDelete.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                if (mTodo != null)
                {
                    mViewModel.deleteTodo(mTodo);
                    getActivity().onBackPressed();
                }
            }
        });
        return vView;
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof TodoDetailFragmentListener)
        {
            mListener = (TodoDetailFragmentListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString() + " must implement TodoDetailFragmentListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    public interface TodoDetailFragmentListener
    {
    }
}
