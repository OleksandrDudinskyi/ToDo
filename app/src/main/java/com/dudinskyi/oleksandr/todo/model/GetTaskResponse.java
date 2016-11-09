package com.dudinskyi.oleksandr.todo.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class GetTaskResponse {
    @SerializedName("data")
    private List<TaskModel> data;

    public List<TaskModel> getData() {
        return data;
    }
}
