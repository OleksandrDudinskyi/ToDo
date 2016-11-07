package com.dudinskyi.oleksandr.todo.network;

import com.dudinskyi.oleksandr.todo.network.api.TaskAPI;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class NetworkService {
    private static NetworkService instance;
    private final TaskAPI taskAPI;

    private NetworkService() {
        taskAPI = new Retrofit.Builder()
                .baseUrl("https://dl.dropboxusercontent.com")
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(new OkHttpClient.Builder().build())
                .build().create(TaskAPI.class);
    }

    public static NetworkService getInstance() {
        synchronized (NetworkService.class) {
            if (instance == null) {
                instance = new NetworkService();
            }
        }
        return instance;
    }

    public TaskAPI getTaskAPI() {
        return taskAPI;
    }
}
