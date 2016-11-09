package com.dudinskyi.oleksandr.todo.presenter;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.database.specification.PendingTasksSpecification;
import com.dudinskyi.oleksandr.todo.model.Task;
import com.dudinskyi.oleksandr.todo.view.PendingTasksView;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTaskPresenter extends Presenter<PendingTasksView> {

    private TaskRepository taskRepository;

    @Override
    public void initialize() {
        taskRepository = new TaskRepository();
        List<Task> tasks = taskRepository.query(new PendingTasksSpecification());
        if (tasks.isEmpty()) {
            view.hideEmptyPlaceHolder();
        } else {
            view.addTasks(tasks);
        }
    }

    @Override
    public void destroy() {

    }
}
