package com.dudinskyi.oleksandr.todo.view.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.model.Task;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskHolder> {

    private List<Task> tasks;

    public TaskAdapter() {
        this.tasks = new ArrayList<>();
    }

    public void addTasks(List<Task> tasks) {
        this.tasks.clear();
        this.tasks.addAll(tasks);
        notifyItemRangeInserted(0, tasks.size());
    }

    public void addTask(Task task) {
        this.tasks.add(task);
        notifyItemInserted(tasks.size() - 1);
    }

    public void deleteTask(Task task) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task nextTask = iterator.next();
            if (nextTask.getId().equals(task.getId())) {
                int taskIndex = tasks.indexOf(nextTask);
                iterator.remove();
                notifyItemRemoved(taskIndex);
            }
        }
    }

    @Override
    public TaskHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new TaskHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.task_item, parent, false));
    }

    @Override
    public void onBindViewHolder(TaskHolder holder, int position) {
        holder.taskName.setText(holder.itemView.getContext().getString(R.string.task_name_pattern, position + 1, tasks.get(position).getName()));
//        holder.container.setOnLongClickListener(view -> {});
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskHolder extends RecyclerView.ViewHolder {

        private TextView taskName;
        private LinearLayout container;

        TaskHolder(View itemView) {
            super(itemView);
            container = (LinearLayout) itemView.findViewById(R.id.container);
            taskName = (TextView) itemView.findViewById(R.id.task_name);

        }
    }
}
