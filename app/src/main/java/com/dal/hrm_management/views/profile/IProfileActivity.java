package com.dal.hrm_management.views.profile;

import com.dal.hrm_management.models.profile.Data;

/**
 * Created by Luu Ngoc Lan on 30-Jul-18.
 */

public interface IProfileActivity {
    public void getProfileSuccess(Data profile);

    public void getProfileFailure();
}
