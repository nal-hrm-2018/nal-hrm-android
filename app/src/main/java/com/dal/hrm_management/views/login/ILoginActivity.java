package com.dal.hrm_management.views.login;

import com.dal.hrm_management.models.LoginModel;

public interface ILoginActivity {
    public void loginSucess(String token);
    public void loginFailure();
}
