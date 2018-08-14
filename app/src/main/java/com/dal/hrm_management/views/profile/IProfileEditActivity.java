package com.dal.hrm_management.views.profile;

import com.dal.hrm_management.models.profile.Profile;

/**
 * Created by Luu Ngoc Lan on 09-Aug-18.
 */

public interface IProfileEditActivity extends IProfileActivity{
    public void editProfileSuccess(Profile profile);

    public void editProfileFailure();
}
