package com.dal.hrm_management.views.employee;

import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.Team;

import java.util.List;

/**
 * Created by Luu Ngoc Lan on 23-Aug-18.
 */

public interface IEditProfileEmployeeActivity extends IBasicEmployeeFragment {

    public void editProfileSuccess(Profile profile);

    public void editProfileFailure();

    public void getTeamsSuccess(List<Team> teams);

    public void getTeamsFailed();
}
