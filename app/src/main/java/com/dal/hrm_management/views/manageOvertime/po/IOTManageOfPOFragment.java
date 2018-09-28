package com.dal.hrm_management.views.manageOvertime.po;

import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.Data;

public interface IOTManageOfPOFragment {

    public void getListProjectInProgressSuccess(DataProject data);

    public void getListProjectInProgressFailed();

    public void getListOTSucess(Data data);

    public void getListOTFailure();

    public void getlistOTError(Throwable t);

    public void updateStatusOvertimeSuccess(String msg);

    public void updateStatusOvertimeFailure();
}
