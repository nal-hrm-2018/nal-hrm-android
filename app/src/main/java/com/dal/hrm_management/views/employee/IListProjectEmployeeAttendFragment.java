package com.dal.hrm_management.views.employee;

import com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess;

import java.util.ArrayList;

public interface IListProjectEmployeeAttendFragment {
    public void Success(ArrayList<ProjectAndProcess> projectAndProcess);
    public void Failure();
    public void PermissionDeny();
}
