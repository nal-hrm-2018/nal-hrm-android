package com.dal.hrm_management.presenters.managerOvertime.po;

public interface IOverTimeManageOfPoPresenter {

    public void getListOverTimeForPO(String idProject, int page, int pageSize);

    public void getListProjectInProgressOfPO(int currentPage, int pageSize);
}
