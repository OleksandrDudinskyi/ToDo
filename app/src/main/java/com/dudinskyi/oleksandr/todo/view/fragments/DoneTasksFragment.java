package com.dudinskyi.oleksandr.todo.view.fragments;

import com.dudinskyi.oleksandr.todo.presenter.DoneTaskPresenter;
import com.dudinskyi.oleksandr.todo.view.DoneTasksView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class DoneTasksFragment extends TasksFragment implements DoneTasksView {

    private DoneTaskPresenter presenter;

    @Override
    protected void initPresenter() {
        presenter = new DoneTaskPresenter();
        presenter.addView(this);
        presenter.initialize();

    }

    @Override
    public void onDestroy() {
        presenter.destroy();
        super.onDestroy();
    }
}
