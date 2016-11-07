package com.dudinskyi.oleksandr.todo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.model.TaskState;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks;

    public TaskAdapter() {
        this.tasks = new ArrayList<>();
    }

    public TaskAdapter(List<Task> tasks) {
        this.tasks = tasks;
    }

    public void addTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyItemRangeInserted(0, tasks.size());
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_layout, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.taskName.setText(tasks.get(position).getName());
        holder.taskStatus.setText(TaskState.getStatus(tasks.get(position).getState()).toString());
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskHolder extends RecyclerView.ViewHolder {

        private TextView taskName;
        private TextView taskStatus;

        TaskHolder(View itemView) {
            super(itemView);
            taskName = (TextView) itemView.findViewById(R.id.task_name);
            taskStatus = (TextView) itemView.findViewById(R.id.task_status);

        }
    }
}
