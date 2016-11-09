package com.dudinskyi.oleksandr.todo.model;

import com.google.gson.annotations.SerializedName;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskModel {
    @SerializedName("id")
    private String id;

    @SerializedName("name")
    private String name;

    @SerializedName("state")
    private Integer state;

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    Integer getState() {
        return state;
    }
}
