package com.dal.hrm_management.views.manageNotifications;

import com.dal.hrm_management.models.dashboard.notification.Notification;

import java.util.List;

public interface INotificationListFragment {
    public void getListNotificationSuccess(List<Notification> notificationList);

    public void getListNotificationFailed();
}
