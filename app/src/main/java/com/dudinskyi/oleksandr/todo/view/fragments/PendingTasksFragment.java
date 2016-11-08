package com.dudinskyi.oleksandr.todo.view.fragments;

import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.presenter.PendingTaskPresenter;
import com.dudinskyi.oleksandr.todo.view.PendingTasksView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTasksFragment extends TasksFragment implements PendingTasksView {

    private PendingTaskPresenter presenter;

    protected void initPresenter() {
        presenter = new PendingTaskPresenter();
        presenter.addView(this);
        presenter.initialize();
    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }

    @Override
    public void addTask(Task task) {
        adapter.addTask(task);
    }

}
