package com.dal.hrm_management.presenters.home;

import android.util.Log;
import android.widget.Toast;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.home.iHomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements IHomePresenter{
    private static  final String TAG = HomePresenter.class.getSimpleName();
    private iHomeActivity ihomeActivity;
    public static int idEmployee;
    public HomePresenter(iHomeActivity iHomeActivity) {
        this.ihomeActivity = iHomeActivity;
    }

    @Override
    public void getProfile(String token) {
        final ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProfileResponse> call = apiService.getProfile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                Log.d(TAG + "code", String.valueOf(response.code()));
                if (response.code() >= 300){
                    ihomeActivity.Failure();
                }else if (response.code() >=200){
                    LoginPresenter.position = response.body().getProfile().getRole().getNameRole();
                    idEmployee = response.body().getProfile().getIdEmployee();
                    ihomeActivity.Success(response.body().getProfile());
                }else{
                    ihomeActivity.Failure();
                }

            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {

            }
        });



    }
}
