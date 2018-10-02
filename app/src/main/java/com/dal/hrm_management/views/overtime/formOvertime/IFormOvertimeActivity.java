package com.dal.hrm_management.views.overtime.formOvertime;

import com.dal.hrm_management.models.holiday.Holiday;
import com.dal.hrm_management.models.listProjectEmpJoining.Project;

import java.util.List;

public interface IFormOvertimeActivity {
    public void getListProjectSuccess(List<Project> data);

    public void getListProjectFailure();

    public void addOvertimeSuccess();

    public void addOvertimeFailure();

    public void addOvertimeFailure(String message);

    public void editOvertimeSuccess(String msg);

    public void editOvertimeFailure();

    void getHolidaySuccess(List<Holiday> data);
}
