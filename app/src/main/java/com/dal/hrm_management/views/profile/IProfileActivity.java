package com.dal.hrm_management.views.profile;

import com.dal.hrm_management.models.profile.Profile;

/**
 * Created by Luu Ngoc Lan on 30-Jul-18.
 */

public interface IProfileActivity {
    public void getProfileSuccess(Profile profile);

    public void getProfileFailure();
}
