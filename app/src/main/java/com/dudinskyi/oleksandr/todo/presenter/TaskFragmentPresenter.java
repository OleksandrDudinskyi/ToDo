package com.dudinskyi.oleksandr.todo.presenter;

import android.util.Log;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.database.specification.DoneTasksSpecification;
import com.dudinskyi.oleksandr.todo.database.specification.PendingTasksSpecification;
import com.dudinskyi.oleksandr.todo.eventbus.RxBus;
import com.dudinskyi.oleksandr.todo.eventbus.UpdateDoneTasks;
import com.dudinskyi.oleksandr.todo.eventbus.UpdatePendingTasks;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.model.TaskState;
import com.dudinskyi.oleksandr.todo.view.TasksFragmentView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

import rx.Completable;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskFragmentPresenter extends Presenter<TasksFragmentView> {

    private static final String TAG = TaskFragmentPresenter.class.getSimpleName();
    private TaskRepository taskRepository;
    private List<Task> tasksToDelete;
    private Map<String, Subscription> moveToDoneSubscriptions;
    private Subscription updateTasksSubscription;

    @Override
    public void initialize() {
        taskRepository = new TaskRepository();
        tasksToDelete = new ArrayList<>();
        moveToDoneSubscriptions = new HashMap<>();
        updateTasksSubscription = RxBus.getInstance().getEvents().subscribe(o -> {
            if (o instanceof UpdateDoneTasks) {
                getDoneTasks();
            } else if (o instanceof UpdatePendingTasks) {
                getPendingTasks();
            }
        }, throwable -> Log.d(TAG, "Update task event error: ", throwable));
    }

    public void onLongClick(Task task) {
        if (!tasksToDelete.contains(task)) {
            task.setHighlighted(true);
            tasksToDelete.add(task);
            view.updateTask(task);
        }
    }

    public void onClick(Task task) {
        if (tasksToDelete.isEmpty() && TaskState.getStatus(task.getState()) == TaskState.PENDING) {
            task.showCancelButton(true);
            view.updateTask(task);
            moveToDoneSubscriptions.put(task.getId(), completeTask()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(() -> {
                        taskRepository.update(task);
                        RxBus.getInstance().postEvent(new UpdateDoneTasks());
                        view.deleteTask(task);
                        Log.d(TAG, "Task was moved to done");
                    }, throwable -> Log.e(TAG, "Task moving to done error : ", throwable)));
        } else {
            if (tasksToDelete.contains(task)) {
                task.setHighlighted(false);
                tasksToDelete.remove(task);
            } else {
                task.setHighlighted(true);
                tasksToDelete.add(task);
            }
            view.updateTask(task);
        }
    }

    public void onCancelClick(Task task) {
        moveToDoneSubscriptions.get(task.getId()).unsubscribe();
        task.showCancelButton(false);
        view.updateTask(task);
    }

    private Completable completeTask() {
        return Completable.timer(5, TimeUnit.SECONDS);
    }

    public void getPendingTasks() {
        List<Task> tasks = taskRepository.query(new PendingTasksSpecification());
        if (tasks.isEmpty()) {
            view.showEmptyPlaceHolder();
        } else {
            view.addTasks(tasks);
        }
    }

    public void getDoneTasks() {
        DoneTasksSpecification specification = new DoneTasksSpecification();
        List<Task> tasks = taskRepository.query(specification);
        if (tasks.isEmpty()) {
            view.hideEmptyPlaceHolder();
        } else {
            view.addTasks(tasks);
        }
    }

    public void deleteTasks() {
        for (Task task : tasksToDelete) {
            taskRepository.remove(task);
            view.deleteTask(task);
        }
    }

    @Override
    public void destroy() {
        for (String key : moveToDoneSubscriptions.keySet()) {
            moveToDoneSubscriptions.get(key).unsubscribe();
        }
        updateTasksSubscription.unsubscribe();
    }

    public void addNewTask(String taskName) {
        Task task = new Task(UUID.randomUUID().toString(), taskName, TaskState.PENDING.getStatus());
        taskRepository.add(task);
        view.addTask(task);
    }
}
