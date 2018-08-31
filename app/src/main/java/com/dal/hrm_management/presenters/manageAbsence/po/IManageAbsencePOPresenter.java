package com.dal.hrm_management.presenters.manageAbsence.po;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public interface IManageAbsencePOPresenter {

    public void getListProject(int currentPage, int pageSize);

    public void getListAbsence(String idProject);

}
