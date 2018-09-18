package com.dal.hrm_management.views.overtime;

import com.dal.hrm_management.models.overtimePersonal.DataOvertime;

public interface IOvertimeListFragment {
    public void getOvertimeSuccess(DataOvertime dataOvertime);

    public void getOvertimeFailure();
}
