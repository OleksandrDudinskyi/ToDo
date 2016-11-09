package com.dudinskyi.oleksandr.todo.database.repository;

import com.dudinskyi.oleksandr.todo.database.specification.Specification;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
abstract class Repository<T> {

    public abstract void add(T task);

    public abstract void add(Iterable<T> tasks);

    public abstract void update(T task);

    public abstract void update(List<T> tasks);

    public abstract void remove(T item);

    public abstract void remove(Specification specification);

    public abstract T find(Specification specification);

    public abstract List<T> query(Specification specification);

}
