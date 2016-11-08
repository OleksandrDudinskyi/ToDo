package com.dudinskyi.oleksandr.todo.presenter;

import com.dudinskyi.oleksandr.todo.view.View;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public abstract class Presenter<T extends View> {
    T view;

    public void addView(T view) {
        this.view = view;
    }

    public abstract void initialize();

    public abstract void destroy();
}
