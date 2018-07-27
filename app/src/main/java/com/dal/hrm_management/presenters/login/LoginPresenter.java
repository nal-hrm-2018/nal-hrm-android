package com.dal.hrm_management.presenters.login;

import com.dal.hrm_management.models.login.GetLoginToken;
import com.dal.hrm_management.views.login.ILoginActivity;

import java.util.concurrent.ExecutionException;

public class LoginPresenter implements ILoginPresenter {
    private ILoginActivity iLoginActivity;
    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
    }

    @Override
    public void getToken(String email, String password) {
        try {
            String token = new GetLoginToken(email,password).execute("http://52.14.120.158/api/login").get();
            if (token != null){
                iLoginActivity.loginSucess();
            }else{
                iLoginActivity.loginFailure();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
    }
}
