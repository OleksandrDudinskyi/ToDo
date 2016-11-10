package com.dudinskyi.oleksandr.todo.presenter;

import android.util.Log;

import com.dudinskyi.oleksandr.todo.database.specification.PendingTasksSpecification;
import com.dudinskyi.oleksandr.todo.database.specification.Specification;
import com.dudinskyi.oleksandr.todo.eventbus.RxBus;
import com.dudinskyi.oleksandr.todo.eventbus.UpdatePendingTasks;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTaskFragmentPresenter extends TaskFragmentPresenter {

    private Specification specification;

    @Override
    public void initialize() {
        specification = new PendingTasksSpecification();
        super.initialize();
        updateTasksSubscription = RxBus.getInstance().getEvents().subscribe(o -> {
            if (o instanceof UpdatePendingTasks) {
                updateTasks();
            }
        }, throwable -> Log.d(TAG, "Update task event error: ", throwable));
    }

    @Override
    Specification getSpecification() {
        return specification;
    }

}
