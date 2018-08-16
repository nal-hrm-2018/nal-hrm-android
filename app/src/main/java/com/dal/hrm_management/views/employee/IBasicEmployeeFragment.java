package com.dal.hrm_management.views.employee;

import com.dal.hrm_management.models.profile.Profile;

/**
 * Created by Luu Ngoc Lan on 16-Aug-18.
 */

public interface IBasicEmployeeFragment {

    public void getBasicEmployeeSuccess(Profile profile);

    public void getBasicEmployeeFailed();
}
