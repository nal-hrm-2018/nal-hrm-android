package com.dal.hrm_management.presenters.splash;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.views.splash.ISplashActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dal.hrm_management.presenters.login.LoginPresenter.token;

/**
 * Created by Luu Ngoc Lan on 13-Sep-18.
 */

public class SplashPresenter implements ISplashPresenter {
    private ISplashActivity iSplashActivity;

    public SplashPresenter(ISplashActivity iSplashActivity) {
        this.iSplashActivity = iSplashActivity;
    }

    @Override
    public void getToken(String email, String password) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> call = apiService.getToKen(email, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                if (response.code() >= 500) {
                    iSplashActivity.errorInServer();
                } else if (response.code() >= 300) {
                    iSplashActivity.loginFailure();
                } else if (response.code() >= 200) {
                    token = response.body().getData();
                    iSplashActivity.loginSucess(token);
                } else {
                    iSplashActivity.errorInServer();
                }
            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                iSplashActivity.errorInServer();
            }
        });
    }
}
