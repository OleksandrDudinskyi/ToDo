package com.dudinskyi.oleksandr.todo.model;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public enum TaskState {
    PENDING(0), DONE(1), UNDEFINED(-1);

    private int status;

    TaskState(int status) {
        this.status = status;
    }

    public static TaskState getStatus(Integer status) {
        if (status == null) {
            return UNDEFINED;
        }
        for (TaskState taskStatus : values()) {
            if (taskStatus.status == status) {
                return taskStatus;
            }
        }
        return UNDEFINED;
    }
}
