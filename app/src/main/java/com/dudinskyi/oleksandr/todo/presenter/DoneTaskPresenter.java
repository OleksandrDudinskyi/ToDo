package com.dudinskyi.oleksandr.todo.presenter;

import com.dudinskyi.oleksandr.todo.database.repository.TaskRepository;
import com.dudinskyi.oleksandr.todo.database.specification.DoneTasksSpecification;
import com.dudinskyi.oleksandr.todo.view.DoneTasksView;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class DoneTaskPresenter extends Presenter<DoneTasksView> {

    private TaskRepository taskRepository;

    @Override
    public void initialize() {
        taskRepository = new TaskRepository();
        view.addTasks(taskRepository.query(new DoneTasksSpecification()));
    }

    @Override
    public void destroy() {

    }
}
