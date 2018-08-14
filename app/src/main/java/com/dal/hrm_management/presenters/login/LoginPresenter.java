package com.dal.hrm_management.presenters.login;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.LoginModel;
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
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<LoginModel> call = apiService.getToKen(auth, email, password);
        call.enqueue(new Callback<LoginModel>() {
            @Override
            public void onResponse(Call<LoginModel> call, Response<LoginModel> response) {

                Log.d("Result_code", String.valueOf(response.body().getResultCode()));
                if (response.body().getResultCode()==200){
                    LoginPresenter.token = response.body().getData();
                    iLoginActivity.loginSucess(token);
                }else{
                    iLoginActivity.loginFailure();
                }


            }

            @Override
            public void onFailure(Call<LoginModel> call, Throwable t) {
                iLoginActivity.loginFailure();
            }
        });
    }
}
