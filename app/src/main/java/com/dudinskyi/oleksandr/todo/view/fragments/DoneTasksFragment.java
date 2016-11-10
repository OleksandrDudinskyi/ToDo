package com.dudinskyi.oleksandr.todo.view.fragments;

import android.view.Menu;
import android.view.MenuInflater;

import com.dudinskyi.oleksandr.todo.presenter.DoneTaskFragmentPresenter;
import com.dudinskyi.oleksandr.todo.view.TasksFragmentView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class DoneTasksFragment extends TasksFragment implements TasksFragmentView {

    @Override
    protected void initPresenter() {
        presenter = new DoneTaskFragmentPresenter();
        presenter.addView(this);
        presenter.initialize();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        initMenuItems(menu);
        addTask.setVisible(false);
        super.onCreateOptionsMenu(menu, inflater);
    }
}
