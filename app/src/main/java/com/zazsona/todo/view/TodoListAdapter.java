package com.zazsona.todo.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.zazsona.todo.R;
import com.zazsona.todo.model.database.Todo;

import java.util.List;

public class TodoListAdapter extends RecyclerView.Adapter<TodoListAdapter.TodoViewHolder>
{
    private List<Todo> mTodos;
    private LayoutInflater mInflater;

    public TodoListAdapter(Context context)
    {
        mInflater = LayoutInflater.from(context);
    }

    public void setTodos(List<Todo> todos)
    {
        this.mTodos = todos;
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        View vTodoItemView = mInflater.inflate(R.layout.listitem_todo, parent, false);
        return new TodoViewHolder(vTodoItemView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoViewHolder holder, int position)
    {
        Todo mTodo = mTodos.get(position);
        holder.vTodoTitle.setText(mTodo.getName());
        holder.vTodoBlurb.setText(mTodo.getDescription());
    }

    @Override
    public int getItemCount()
    {
        return mTodos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder
    {
        private TodoListAdapter mAdapter;

        private TextView vTodoTitle;
        private TextView vTodoBlurb;
        private ImageView vTodoImage;

        public TodoViewHolder(@NonNull View itemView, TodoListAdapter adapter)
        {
            super(itemView);
            vTodoTitle = itemView.findViewById(R.id.todoListItemTitle);
            vTodoBlurb = itemView.findViewById(R.id.todoListItemBlurb);
            vTodoImage = itemView.findViewById(R.id.todoListItemImage);
            this.mAdapter = adapter;
        }
    }
}
