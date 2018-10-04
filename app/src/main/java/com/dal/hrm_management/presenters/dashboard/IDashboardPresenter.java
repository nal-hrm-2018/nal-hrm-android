package com.dal.hrm_management.presenters.dashboard;

public interface IDashboardPresenter {
    public void getJoiningProject(int currentPage, int pageSize);

    public void getProjectCompanyRunning();

    public void getInforEventInThisMonth();

    public void getNotification();

}
