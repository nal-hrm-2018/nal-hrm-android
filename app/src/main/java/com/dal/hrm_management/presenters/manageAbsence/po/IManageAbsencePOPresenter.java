package com.dal.hrm_management.presenters.manageAbsence.po;

public interface IManageAbsencePOPresenter {

    public void getListProject(int currentPage, int pageSize);

    public void getListAbsence(String idProject, int currentPage, int pageSize);

}
