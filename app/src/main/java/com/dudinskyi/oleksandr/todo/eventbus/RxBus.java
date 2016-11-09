package com.dudinskyi.oleksandr.todo.eventbus;

import rx.Observable;
import rx.subjects.PublishSubject;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class RxBus {
    private static RxBus instance;

    private PublishSubject<Object> subject = PublishSubject.create();

    public static RxBus getInstance() {
        if (instance == null) {
            instance = new RxBus();
        }
        return instance;
    }

    public void postEvent(Object object) {
        subject.onNext(object);
    }

    public Observable<Object> getEvents() {
        return subject;
    }
}
