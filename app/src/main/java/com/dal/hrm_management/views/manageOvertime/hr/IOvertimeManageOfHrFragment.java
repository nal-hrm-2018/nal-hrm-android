package com.dal.hrm_management.views.manageOvertime.hr;

import com.dal.hrm_management.models.manageOvertime.hr.viewList.DataOvertime;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

public interface IOvertimeManageOfHrFragment {
    public void getOvertimeListSuccess(DataOvertime dataOvertime);

    public void getOvertimeListFailure();
}
