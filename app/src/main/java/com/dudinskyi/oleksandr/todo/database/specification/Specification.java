package com.dudinskyi.oleksandr.todo.database.specification;


import android.support.annotation.Nullable;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public interface Specification<T extends RealmObject> {

    RealmResults<T> toRealmResults(Realm realm);

    @Nullable
    T toRealmResult(Realm realm);
}
