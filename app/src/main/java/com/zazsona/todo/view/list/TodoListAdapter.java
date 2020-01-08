package com.zazsona.todo.view;

import android.content.Context;
import android.content.Intent;
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

    /**
     * Sets the {@link Todo} list to adapt.
     * @param todos the list to adapt
     */
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
    public void onBindViewHolder(@NonNull final TodoViewHolder holder, int position)
    {
        final Todo mTodo = mTodos.get(position);
        holder.vTodoTitle.setText(mTodo.getName());

        String descriptionPreview = (mTodo.getDescription().length() > 20) ? mTodo.getDescription().substring(0, 30) : mTodo.getDescription();
        descriptionPreview = descriptionPreview.replace("\n", " ");
        holder.vTodoBlurb.setText(descriptionPreview);

        if (mTodo.isComplete())
        {
            holder.vTodoImage.setImageResource(R.drawable.complete);
        }
        else
        {
            holder.vTodoImage.setImageResource(R.drawable.incomplete);
        }
        holder.vView.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent detailIntent = TodoDetailActivity.newIntent(holder.vView.getContext(), mTodo);
                holder.vView.getContext().startActivity(detailIntent);
            }
        });
    }

    @Override
    public int getItemCount()
    {
        return mTodos.size();
    }

    class TodoViewHolder extends RecyclerView.ViewHolder
    {
        private final TodoListAdapter mAdapter;

        private final TextView vTodoTitle;
        private final TextView vTodoBlurb;
        private final ImageView vTodoImage;
        private final View vView;

        public TodoViewHolder(@NonNull final View itemView, TodoListAdapter adapter)
        {
            super(itemView);
            vTodoTitle = itemView.findViewById(R.id.todoListItemTitle);
            vTodoBlurb = itemView.findViewById(R.id.todoListItemBlurb);
            vTodoImage = itemView.findViewById(R.id.todoListItemImage);
            vView = itemView;
            this.mAdapter = adapter;
        }
    }
}
