package com.dal.hrm_management.views.login;

public interface ILoginActivity {
    public void loginSucess(String token);
    public void loginFailure();
    public  void errorInServer();
}
