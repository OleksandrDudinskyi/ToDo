package com.dudinskyi.oleksandr.todo.presenter;

import android.util.Log;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.model.TaskState;
import com.dudinskyi.oleksandr.todo.network.NetworkService;
import com.dudinskyi.oleksandr.todo.view.TasksView;

import java.io.IOException;
import java.util.UUID;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskPresenter extends Presenter<TasksView> {

    private static final String TAG = TaskPresenter.class.getSimpleName();
    private CompositeSubscription compositeSubscription;
    private TaskRepository taskRepository;

    @Override
    public void initialize() {
        compositeSubscription = new CompositeSubscription();
        taskRepository = new TaskRepository();
    }

    @Override
    public void destroy() {
        compositeSubscription.unsubscribe();
    }

    public void addNewTask(String taskName) {
        Task task = new Task(UUID.randomUUID().toString(), taskName, TaskState.PENDING.getStatus());
        taskRepository.add(task);
    }

    public void updateTasks() {
        compositeSubscription.clear();
        compositeSubscription.add(NetworkService.getInstance().getTaskAPI().getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getTaskResponse -> {
                            taskRepository.update(getTaskResponse.getData());
                            view.onTaskUpdated();
                        }, throwable -> {
                            Log.e(TAG, "Get tasks error: ", throwable);
                            if (throwable instanceof IOException) {
                                view.noInternetConnection();
                            } else {
                                view.onTaskUpdatedError();
                            }
                        }
                ));
    }
}
