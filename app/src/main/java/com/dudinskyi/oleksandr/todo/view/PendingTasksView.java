package com.dudinskyi.oleksandr.todo.view;

import com.dudinskyi.oleksandr.todo.model.Task;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public interface PendingTasksView extends TasksFragmentView {

    void addTask(Task task);

}
