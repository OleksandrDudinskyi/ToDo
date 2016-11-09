package com.dudinskyi.oleksandr.todo.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.view.TasksFragmentView;
import com.dudinskyi.oleksandr.todo.view.adapter.TaskAdapter;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public abstract class TasksFragment extends Fragment implements TasksFragmentView {

    protected TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tasks_list, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        textView = (TextView) view.findViewById(R.id.empty_place_holder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        initPresenter();

    }

    protected abstract void initPresenter();

    @Override
    public void addTasks(List<Task> tasks) {
        recyclerView.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        adapter.addTasks(tasks);
    }

    @Override
    public void showEmptyPlaceHolder() {
        progressBar.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideEmptyPlaceHolder() {
        textView.setVisibility(View.GONE);
    }

    @Override
    public void deleteTask(Task task) {
        adapter.deleteTask(task);
    }
}
