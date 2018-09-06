package com.dal.hrm_management.presenters.managerOvertime.po;

import com.dal.hrm_management.views.manageOvertime.po.IOTManageOfPOFragment;

/**
 * Created by Luu Ngoc Lan on 06-Sep-18.
 */

public class OverTimeManageOfPoPresenter implements IOverTimeManageOfPoPresenter{

    private IOTManageOfPOFragment iotManageOfPOFragment;

    public OverTimeManageOfPoPresenter(IOTManageOfPOFragment iotManageOfPOFragment) {
        this.iotManageOfPOFragment = iotManageOfPOFragment;
    }
}
