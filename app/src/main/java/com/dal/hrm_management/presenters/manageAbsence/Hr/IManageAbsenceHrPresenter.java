package com.dal.hrm_management.presenters.manageAbsence.Hr;

public interface IManageAbsenceHrPresenter {
    public void getListAbsence(int page, int pageSize);

    public void deleteAbsence(int id);

    public void searchAbsenceWithMonth(int month, int year, int page, int pageSize);

}
