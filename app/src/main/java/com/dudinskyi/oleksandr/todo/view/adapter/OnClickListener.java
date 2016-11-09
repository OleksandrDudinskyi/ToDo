package com.dudinskyi.oleksandr.todo.view.adapter;

import com.dudinskyi.oleksandr.todo.model.Task;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public interface OnClickListener {
    void onLongClick(Task task);

    void onClick(Task task);

    void onCancelClick(Task task);
}
