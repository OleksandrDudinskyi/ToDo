package com.dudinskyi.oleksandr.todo.model;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class Task {
    private String id;
    private String name;
    private Integer state;

    public Task(String id, String name, Integer state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getState() {
        return state;
    }
}
