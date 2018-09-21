package com.dal.hrm_management.views.manageOvertime.po;

import com.dal.hrm_management.models.manageOvertime.po.viewlist.Data;

/**
 * Created by Luu Ngoc Lan on 06-Sep-18.
 */

public interface IOTManageOfPOFragment {
    public void getListOTSucess(Data data);
    public void getListOTFailure();
    public void getlistOTError(Throwable t);
}
