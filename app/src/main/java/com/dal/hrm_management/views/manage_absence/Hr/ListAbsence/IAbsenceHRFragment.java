package com.dal.hrm_management.views.manage_absence.Hr.ListAbsence;

import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;

import java.util.List;

public interface IAbsenceHRFragment {
    public void getListAbsenceSuccess(int total, List<ListAbsenceForHr> list);
    public void getListAbsenceFailure();
    public void deleteAbsenceSuccess();
    public void deleteAbsenceFailure();
}
