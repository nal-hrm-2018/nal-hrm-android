package com.dal.hrm_management.views.manageNotifications;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.dal.hrm_management.R;
import com.dal.hrm_management.adapter.listNotifications.NotificationListAdapter;
import com.dal.hrm_management.models.dashboard.notification.Notification;
import com.dal.hrm_management.presenters.notifications.NotificationListPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class NotificationsListFragment extends Fragment implements INotificationListFragment, View.OnClickListener {

    private List<Notification> notificationList;
    private RecyclerView rvNotifications;
    private NotificationListAdapter adapter;
    private NotificationListPresenter notificationListPresenter;
    private FloatingActionButton btnAdd;

    public NotificationsListFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_notifications_list, container, false);
        init(view);
        getData();
        return view;
    }

    private void getData() {
        notificationListPresenter.getNotificationList();
    }

    private void init(View view) {
        rvNotifications = view.findViewById(R.id.rv_notifications);
        rvNotifications.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        notificationList = new ArrayList<>();
        notificationListPresenter = new NotificationListPresenter(this);
        btnAdd = view.findViewById(R.id.btn_add);
        btnAdd.setOnClickListener(this);
    }

    @Override
    public void getListNotificationSuccess(List<Notification> notificationList) {
        this.notificationList = notificationList;
        adapter = new NotificationListAdapter(getActivity(),notificationList);
        rvNotifications.setAdapter(adapter);
    }

    @Override
    public void getListNotificationFailed() {

    }

    @Override
    public void onClick(View v) {

    }
}
