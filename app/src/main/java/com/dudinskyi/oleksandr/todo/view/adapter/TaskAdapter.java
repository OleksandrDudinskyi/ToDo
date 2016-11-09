package com.dudinskyi.oleksandr.todo.view.adapter;

import android.support.v4.content.ContextCompat;
import android.support.v7.util.SortedList;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;
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
    private OnClickListener onClickListener;

    public TaskAdapter(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
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

    public void updateTask(Task task) {
        int itemPosition = tasks.indexOf(task);
        if (itemPosition != SortedList.INVALID_POSITION) {
            this.tasks.set(itemPosition, task);
            notifyItemChanged(itemPosition);
        }
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
        Task task = tasks.get(position);
        holder.taskName.setText(holder.itemView.getContext().getString(R.string.task_name_pattern, position + 1, task.getName()));
        holder.container.setTag(task);
        holder.container.setOnLongClickListener(view -> {
            onClickListener.onLongClick((Task) view.getTag());
            return true;
        });
        holder.container.setOnClickListener(view -> onClickListener.onClick((Task) view.getTag()));
        if (task.isHighlighted()) {
            holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.darker_gray));
        }

        if (task.isHighlighted()) {
            holder.container.setBackgroundColor(ContextCompat.getColor(holder.itemView.getContext(), android.R.color.darker_gray));
        }
        if (task.shouldShowCancelButton()) {
            holder.cancelBtn.setTag(task);
            holder.cancelBtn.setEnabled(true);
            holder.cancelBtn.setVisibility(View.VISIBLE);
            holder.cancelBtn.setOnClickListener(view -> onClickListener.onCancelClick(task));
        } else {
            holder.cancelBtn.setEnabled(false);
            holder.cancelBtn.setVisibility(View.INVISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }

    static class TaskHolder extends RecyclerView.ViewHolder {

        private TextView taskName;
        private RelativeLayout container;
        private Button cancelBtn;

        TaskHolder(View itemView) {
            super(itemView);
            container = (RelativeLayout) itemView.findViewById(R.id.container);
            taskName = (TextView) itemView.findViewById(R.id.task_name);
            cancelBtn = (Button) itemView.findViewById(R.id.cancel_btn);

        }
    }
}
