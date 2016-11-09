package com.dudinskyi.oleksandr.todo.view;

import com.dudinskyi.oleksandr.todo.model.Task;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public interface TasksFragmentView extends View {

    void addTasks(List<Task> tasks);

    void addTask(Task task);

    void updateTask(Task task);

    void showEmptyPlaceHolder();

    void hideEmptyPlaceHolder();

    void deleteTask(Task task);
}
