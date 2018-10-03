package com.dal.hrm_management.views.dashboard;

import com.dal.hrm_management.models.absence.DataAbsence;
import com.dal.hrm_management.models.eventInMonth.DataEvent;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;
import com.dal.hrm_management.models.overtimePersonal.DataOvertime;

import java.util.List;

public interface IDashboardFragment {
    public void getInforAbsenceSuccess(DataAbsence dataAbsence);

    public void getInforAbsenceFailure();

    public void getInforOvertimeSuccess(DataOvertime dataOvertime);

    public void getInforOvertimrFailure();

    public void getInforJoiningProjectSuccess(List<Project> list);

    public void getInforJoiningProjectFailure();

    public void getInforProjectsCompanySuccess(List<com.dal.hrm_management.models.projectCompany.Project> list);

    public void getInforProjectsCompanyFailure();

    public void getInforEventInThisMonthSuccess(DataEvent dataEvent);

    public void getInforEventInThisMonthFailure();


}
