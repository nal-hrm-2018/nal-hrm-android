package com.dal.hrm_management.presenters.login;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.views.login.ILoginActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginPresenter implements ILoginPresenter {
    private static  final String TAG = LoginPresenter.class.getSimpleName();
    public static String token;
    public static String position;// HR/DEV/PO/BO/SM: position of member login
    private ILoginActivity iLoginActivity;

    public LoginPresenter(ILoginActivity iLoginActivity) {
        this.iLoginActivity = iLoginActivity;
    }

    @Override
    public void getToken(String email, String password) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<LoginModel> call = apiService.getToKen(email, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {
                Log.d("Login presenter", String.valueOf(response.code()));
                if (response.code() >= 500){
                    iLoginActivity.errorInServer();
                }else if (response.code() >= 300){
                    iLoginActivity.loginFailure();
                }else if (response.code() >=200){
                    LoginPresenter.token = response.body().getData();
                    iLoginActivity.loginSucess(token);
                }else{
                    iLoginActivity.errorInServer();
                }
            }
            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                iLoginActivity.errorInServer();
            }
        });
    }
}
