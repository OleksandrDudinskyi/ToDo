package com.dudinskyi.oleksandr.todo.view.fragments;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.view.TasksFragmentView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTasksFragment extends TasksFragment implements TasksFragmentView {

    public static final int ADD_NEW_TASK_REQUEST_CODE = 0xbc1;

    protected void initPresenter() {
        super.initPresenter();
        presenter.getPendingTasks();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        initMenuItems(menu);
        addTask.setVisible(true);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.add_task:
                AddTaskDialog dialogFragment = new AddTaskDialog();
                dialogFragment.setTargetFragment(this, ADD_NEW_TASK_REQUEST_CODE);
                dialogFragment.show(getFragmentManager(), dialogFragment.getTag());
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
            case ADD_NEW_TASK_REQUEST_CODE:
                if (resultCode == Activity.RESULT_OK) {
                    Bundle bundle = data.getExtras();
                    String taskName = bundle.getString(AddTaskDialog.NEW_TASK_NAME_KEY, "");
                    presenter.addNewTask(taskName);
                }
                break;
        }
    }

}
