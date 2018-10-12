package com.dal.hrm_management.presenters.notifications;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.dashboard.notification.DashboardNotificationResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageNotifications.INotificationListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NotificationListPresenter implements INotificationListPresenter {
    private INotificationListFragment iNotificationListFragment;

    public NotificationListPresenter(INotificationListFragment iNotificationListFragment) {
        this.iNotificationListFragment = iNotificationListFragment;
    }

    @Override
    public void getNotificationList() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DashboardNotificationResponse> call = apiService.getNotification(LoginPresenter.token);
        call.enqueue(new Callback<DashboardNotificationResponse>() {
            @Override
            public void onResponse(Call<DashboardNotificationResponse> call, Response<DashboardNotificationResponse> response) {
                if (response.code() == 200) {
                    iNotificationListFragment.getListNotificationSuccess(response.body().getData());
                } else {
                    iNotificationListFragment.getListNotificationFailed();
                }
            }

            @Override
            public void onFailure(Call<DashboardNotificationResponse> call, Throwable t) {
                iNotificationListFragment.getListNotificationFailed();
            }
        });
    }
}
