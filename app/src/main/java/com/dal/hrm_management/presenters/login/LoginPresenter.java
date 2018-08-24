package com.dal.hrm_management.presenters.login;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.views.login.ILoginActivity;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {
    public static String token;
    public static String position;
    private ILoginActivity iLoginActivity;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
    }

    @Override
    public void getToken(String email, String password) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> call = apiService.getToKen(email, password);
        try{
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.body() == null){
                    iLoginActivity.loginFailure();
                }else if (response.body().getResultCode() == 200) {
                    LoginPresenter.token = response.body().getData();
                    iLoginActivity.loginSucess(token);
                } else if (response.body().getResultCode() == 422) {
                    iLoginActivity.loginFailure();
                } else {
                    iLoginActivity.errorInServer();
                }

            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                iLoginActivity.errorInServer();
            }
        });}catch (Exception ex){
            Log.d("Login Presenter : ",ex.getMessage());
            iLoginActivity.loginFailure();
        }
    }
}
