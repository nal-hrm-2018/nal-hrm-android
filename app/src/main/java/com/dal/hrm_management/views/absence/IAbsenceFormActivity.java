package com.dal.hrm_management.views.absence;

import java.util.List;

public interface IAbsenceFormActivity {
    public void Success();
    public void Failure();
    public void loadTypeAbsenceSuccess(List list);
    public void loadTypeAbsenceFailure();

    public void EditAbsenceSuccess();
    public void EditAbsenceFailure();
}
