package com.dudinskyi.oleksandr.todo.view;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public interface TasksView extends View {

    void onTaskUpdated();

    void onTaskUpdatedError();

    void noInternetConnection();
}
