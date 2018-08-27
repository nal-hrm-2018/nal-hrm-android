package com.dal.hrm_management.presenters.absence;

import com.dal.hrm_management.models.absence.addAbsence.AbsenceForm;

public interface IAbsencePresenter {
    public void getDataAbsence(int currentPage, int pageSize);
    public void addAbsence(String json);
}
