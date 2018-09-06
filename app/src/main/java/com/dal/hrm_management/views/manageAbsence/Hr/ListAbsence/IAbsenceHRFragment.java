package com.dal.hrm_management.views.manageAbsence.Hr.ListAbsence;

import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;

import java.util.List;

public interface IAbsenceHRFragment {
    public void getListAbsenceSuccess(int total, List<ListAbsenceForHr> list);
    public void getListAbsenceFailure();
    public void deleteAbsenceSuccess();
    public void deleteAbsenceFailure();
    public void searchAbsenceSuccess(int total, List<ListAbsenceForHr> list);
    public void searchAbsenceFailed();
}
