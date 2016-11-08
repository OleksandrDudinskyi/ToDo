package com.dudinskyi.oleksandr.todo.presenter;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.database.specification.PendingTasksSpecification;
import com.dudinskyi.oleksandr.todo.view.PendingTasksView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTaskPresenter extends Presenter<PendingTasksView> {

    private TaskRepository taskRepository;

    @Override
    public void initialize() {
        taskRepository = new TaskRepository();
        view.addTasks(taskRepository.query(new PendingTasksSpecification()));
    }

    @Override
    public void destroy() {

    }
}
