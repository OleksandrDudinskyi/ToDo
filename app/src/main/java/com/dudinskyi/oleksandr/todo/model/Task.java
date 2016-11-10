package com.dudinskyi.oleksandr.todo.model;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class Task {
    private String id;
    private String name;
    private Integer state;
    private boolean highlight;
    private boolean showCancelButton;

    public Task(String id, String name, Integer state) {
        this.id = id;
        this.name = name;
        this.state = state;
    }

    public Task(TaskModel taskModel) {
        this.id = taskModel.getId();
        this.name = taskModel.getName();
        this.state = taskModel.getState();
    }

    public void showCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(TaskState taskState) {
        state = taskState.getStatus();
    }

    public boolean isHighlighted() {
        return highlight;
    }

    public void setHighlighted(boolean highlighted) {
        this.highlight = highlighted;
    }

    public boolean shouldShowCancelButton() {
        return showCancelButton;
    }

    @Override
    public int hashCode() {
        return id.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Task)) {
            return false;
        }

        Task task = (Task) obj;
        return id == null ? task.id == null : id.equals(task.id);
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", state='" + state + '\'' +
                ", setHighlighted='" + highlight + '\'' +
                '}';
    }
}
