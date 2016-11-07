package com.dudinskyi.oleksandr.todo.network.api;

import com.dudinskyi.oleksandr.todo.model.GetTaskResponse;

import retrofit2.http.GET;
import rx.Observable;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public interface TaskAPI {
    @GET("https://dl.dropboxusercontent.com/u/6890301/tasks.json")
    Observable<GetTaskResponse> getTasks();
}
