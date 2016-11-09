package com.dudinskyi.oleksandr.todo.presenter;

import android.util.Log;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.eventbus.RxBus;
import com.dudinskyi.oleksandr.todo.eventbus.UpdateDoneTasks;
import com.dudinskyi.oleksandr.todo.eventbus.UpdatePendingTasks;
import com.dudinskyi.oleksandr.todo.model.GetTaskResponse;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.network.NetworkService;
import com.dudinskyi.oleksandr.todo.view.TasksView;

import java.io.IOException;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskActivityPresenter extends Presenter<TasksView> {

    private static final String TAG = TaskActivityPresenter.class.getSimpleName();
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

    public void updateTasks() {
        compositeSubscription.clear();
        compositeSubscription.add(NetworkService.getInstance().getTaskAPI().getTasks()
                .flatMapIterable(GetTaskResponse::getData)
                .map(Task::new)
                .toList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(taskList -> {
                    taskRepository.update(taskList);
                            view.onTaskUpdated();
                    RxBus.getInstance().postEvent(new UpdatePendingTasks());
                    RxBus.getInstance().postEvent(new UpdateDoneTasks());
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
