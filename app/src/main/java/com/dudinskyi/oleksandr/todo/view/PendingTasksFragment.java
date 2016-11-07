package com.dudinskyi.oleksandr.todo.view;

import android.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dudinskyi.oleksandr.todo.R;
import com.dudinskyi.oleksandr.todo.network.NetworkService;
import com.dudinskyi.oleksandr.todo.view.adapter.TaskAdapter;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class PendingTasksFragment extends Fragment {

    private RecyclerView recyclerView;
    private TaskAdapter adapter;
    private CompositeSubscription compositeSubscription;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.pending_tasks, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        adapter = new TaskAdapter();
        recyclerView.setAdapter(adapter);
        compositeSubscription = new CompositeSubscription();
        compositeSubscription.add(NetworkService.getInstance().getTaskAPI().getTasks()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(getTaskResponse -> adapter.addTasks(getTaskResponse.getData()),
                        throwable -> Log.e("PendingTasksFragment", "Get tasks error", throwable)));
    }

    @Override
    public void onDestroy() {
        compositeSubscription.unsubscribe();
        super.onDestroy();
    }
}
