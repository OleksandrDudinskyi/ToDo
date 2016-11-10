package com.dudinskyi.oleksandr.todo.view.fragments;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.presenter.TaskFragmentPresenter;
import com.dudinskyi.oleksandr.todo.view.TasksFragmentView;
import com.dudinskyi.oleksandr.todo.view.adapter.OnClickListener;
import com.dudinskyi.oleksandr.todo.view.adapter.TaskAdapter;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public abstract class TasksFragment extends Fragment implements TasksFragmentView, OnClickListener {

    protected MenuItem addTask;
    protected MenuItem removeTask;
    protected TaskFragmentPresenter presenter;
    private TaskAdapter adapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private TextView textView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.tasks_list, container, false);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        progressBar = (ProgressBar) view.findViewById(R.id.progress_bar);
        textView = (TextView) view.findViewById(R.id.empty_place_holder);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        adapter = new TaskAdapter(this);
        recyclerView.setAdapter(adapter);
        initPresenter();

    }

    public void onLongClick(Task task) {
        presenter.onLongClick(task);
        addTask.setVisible(false);
        removeTask.setVisible(true);
    }

    public void onClick(Task task) {
        presenter.onClick(task);
    }


    public void onCancelClick(Task task) {
        presenter.onCancelClick(task);
    }

    protected abstract void initPresenter();

    public void updateTask(Task task) {
        adapter.updateTask(task);
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

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
    public void addTask(Task task) {
        adapter.addTask(task);
    }

    @Override
    public void hideEmptyPlaceHolder() {
        textView.setVisibility(View.GONE);
    }

    @Override
    public void deleteTask(Task task) {
        adapter.deleteTask(task);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.remove_task:
                presenter.deleteTasks();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    protected void initMenuItems(Menu menu) {
        addTask = menu.findItem(R.id.add_task);
        removeTask = menu.findItem(R.id.remove_task);
    }

    @Override
    public void disableRemove() {
        removeTask.setVisible(false);
    }
}
