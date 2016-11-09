package com.dudinskyi.oleksandr.todo.database.specification;

import com.dudinskyi.oleksandr.todo.database.model.RealmTaskData;

import io.realm.Realm;
import io.realm.RealmResults;

public class PendingTasksSpecification implements Specification<RealmTaskData> {

    @Override
    public RealmResults<RealmTaskData> toRealmResults(Realm realm) {
        return realm.where(RealmTaskData.class).
                equalTo(RealmTaskData.stateField, 0).findAll();
    }

    @Override
    public RealmTaskData toRealmResult(Realm realm) {
        return realm.where(RealmTaskData.class).
                equalTo(RealmTaskData.stateField, 0).findFirst();
    }

}
