package com.dal.hrm_management.views.splash;

/**
 * Created by Luu Ngoc Lan on 13-Sep-18.
 */

public interface ISplashActivity {

    public void loginSucess(String token);

    public void loginFailure();

    public void errorInServer();
}
