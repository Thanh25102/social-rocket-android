package tech.mobile.socialrocket.ui.adapter;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.RecyclerView;
import tech.mobile.socialrocket.R;
import tech.mobile.socialrocket.data.remote.model.TodoApiRes;
import tech.mobile.socialrocket.databinding.TodoItemsBinding;

import java.util.List;

public class TodoAdapter extends RecyclerView.Adapter<TodoAdapter.TodoViewHolder> {

    private final List<TodoApiRes> todos;

    public TodoAdapter(List<TodoApiRes> todos) {
        this.todos = todos;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setTodoList(List<TodoApiRes> todos) {
        this.todos.clear();
        this.todos.addAll(todos);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TodoViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int index) {
        TodoItemsBinding binding = DataBindingUtil
                .inflate(LayoutInflater.from(viewGroup.getContext()), R.layout.todo_items, viewGroup, false);

        return new TodoViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull TodoAdapter.TodoViewHolder holder, int index) {
        TodoApiRes todo = todos.get(index);
        holder.binding.setTodoViewModel(todo);
    }

    @Override
    public int getItemCount() {
        return todos != null ? todos.size() : 0;
    }

    public static class TodoViewHolder extends RecyclerView.ViewHolder {
        private final TodoItemsBinding binding;

        public TodoViewHolder(@NonNull TodoItemsBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
