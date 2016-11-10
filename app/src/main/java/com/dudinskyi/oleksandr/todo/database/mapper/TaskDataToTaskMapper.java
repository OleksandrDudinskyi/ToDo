package com.dudinskyi.oleksandr.todo.database.mapper;

import com.dudinskyi.oleksandr.todo.database.model.RealmTaskData;
import com.dudinskyi.oleksandr.todo.model.Task;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class TaskDataToTaskMapper implements Mapper<RealmTaskData, Task> {

    @Override
    public Task mapFrom(RealmTaskData realmTaskData) {
        Task task = new Task(realmTaskData.getId(), realmTaskData.getName(), realmTaskData.getState());
        task.setHighlighted(realmTaskData.isHighlight());
        task.showCancelButton(realmTaskData.isShowCancelButton());
        return task;
    }

    @Override
    public RealmTaskData mapTo(Task from) {
        RealmTaskData realmTaskData = new RealmTaskData();
        realmTaskData.setId(from.getId());
        realmTaskData.setName(from.getName());
        realmTaskData.setState(from.getState());
        realmTaskData.setHighlight(from.isHighlighted());
        realmTaskData.setShowCancelButton(from.shouldShowCancelButton());
        return realmTaskData;
    }
}
