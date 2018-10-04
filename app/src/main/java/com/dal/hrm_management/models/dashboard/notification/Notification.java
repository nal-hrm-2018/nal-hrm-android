package com.dal.hrm_management.models.dashboard.notification;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Notification {

    @SerializedName("idNotification")
    @Expose
    private Integer idNotification;
    @SerializedName("title")
    @Expose
    private String title;
    @SerializedName("content")
    @Expose
    private String content;
    @SerializedName("endDate")
    @Expose
    private String endDate;
    @SerializedName("createdAt")
    @Expose
    private String createdAt;
    @SerializedName("updateAt")
    @Expose
    private Object updateAt;
    @SerializedName("notificationTypes")
    @Expose
    private NotificationTypes notificationTypes;

    public Integer getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Integer idNotification) {
        this.idNotification = idNotification;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public Object getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Object updateAt) {
        this.updateAt = updateAt;
    }

    public NotificationTypes getNotificationTypes() {
        return notificationTypes;
    }

    public void setNotificationTypes(NotificationTypes notificationTypes) {
        this.notificationTypes = notificationTypes;
    }

}