package com.dudinskyi.oleksandr.todo.database.repository;

import android.support.annotation.Nullable;

import com.dudinskyi.oleksandr.todo.database.mapper.TaskDataToTaskMapper;
import com.dudinskyi.oleksandr.todo.database.model.RealmTaskData;
import com.dudinskyi.oleksandr.todo.database.specification.Specification;
import com.dudinskyi.oleksandr.todo.model.Task;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import io.realm.RealmResults;
import rx.Observable;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskRepository extends Repository<Task> {

    private final TaskDataToTaskMapper mapper;

    public TaskRepository() {
        this.mapper = new TaskDataToTaskMapper();
    }

    @Override
    public void add(Task task) {
        final Realm realm = Realm.getDefaultInstance();
        RealmTaskData realmTaskData = mapper.mapTo(task);
        realm.beginTransaction();
        realm.copyToRealm(realmTaskData);
        realm.commitTransaction();
        realm.close();

    }

    @Override
    public void add(Iterable<Task> tasks) {
        List<RealmTaskData> realmTaskDataList = new ArrayList<>();
        for (Task task : tasks) {
            realmTaskDataList.add(mapper.mapTo(task));
        }
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealm(realmTaskDataList);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void update(Task task) {
        final Realm realm = Realm.getDefaultInstance();
        RealmTaskData realmTaskData = mapper.mapTo(task);
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmTaskData);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void update(List<Task> tasks) {
        List<RealmTaskData> realmTaskDataList = new ArrayList<>();
        for (Task task : tasks) {
            realmTaskDataList.add(mapper.mapTo(task));
        }
        final Realm realm = Realm.getDefaultInstance();
        realm.beginTransaction();
        realm.copyToRealmOrUpdate(realmTaskDataList);
        realm.commitTransaction();
        realm.close();
    }

    @Override
    public void remove(Task item) {
        final Realm realm = Realm.getDefaultInstance();
        RealmTaskData realmTaskData = realm.where(RealmTaskData.class).equalTo(RealmTaskData.idField, item.getId()).findFirst();
        realm.beginTransaction();
        realmTaskData.deleteFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    @SuppressWarnings("unchecked")
    public void remove(Specification specification) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<RealmTaskData> realmResults = specification.toRealmResults(realm);
        realm.beginTransaction();
        realmResults.deleteAllFromRealm();
        realm.commitTransaction();
        realm.close();
    }

    @Override
    @Nullable
    public Task find(Specification specification) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmTaskData realmResult = (RealmTaskData) specification.toRealmResult(realm);
        if (realmResult == null) {
            realm.close();
            return null;
        }
        Task user = mapper.mapFrom(realmResult);
        realm.close();
        return user;
    }

    @Override
    public Observable<Task> rxFind(Specification specification) {
        return Observable.just(find(specification));
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<Task> query(Specification specification) {
        final Realm realm = Realm.getDefaultInstance();
        final RealmResults<RealmTaskData> realmResults = specification.toRealmResults(realm);
        final List<Task> users = new ArrayList<>();
        for (RealmTaskData userData : realmResults) {
            users.add(mapper.mapFrom(userData));
        }
        realm.close();
        return users;
    }

}
