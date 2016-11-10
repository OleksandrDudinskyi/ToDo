package com.dudinskyi.oleksandr.todo.database.model;

import io.realm.RealmObject;
import io.realm.annotations.Ignore;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.Required;

/**
 * @author Oleksandr Dudinskyi (dudinskyj@gmail.com)
 */
public class RealmTaskData extends RealmObject {

    @Ignore
    public static final String idField = "id";

    @Ignore
    public static final String stateField = "state";

    @Required
    @PrimaryKey
    private String id;
    private String name;
    private Integer state;
    private boolean highlight;
    private boolean showCancelButton;

    public RealmTaskData() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public boolean isHighlight() {
        return highlight;
    }

    public void setHighlight(boolean highlight) {
        this.highlight = highlight;
    }

    public boolean isShowCancelButton() {
        return showCancelButton;
    }

    public void setShowCancelButton(boolean showCancelButton) {
        this.showCancelButton = showCancelButton;
    }
}
