package com.dal.hrm_management.presenters.home;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.Data;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.views.home.HomePageActivity;
import com.dal.hrm_management.views.home.iHomeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomePresenter implements IHomePresenter{
    private iHomeActivity ihomeActivity;
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
                Log.d("Homepage", String.valueOf(response.body().getResultCode()));
                if (response.body().getResultCode()==200){
                    ihomeActivity.Success(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                ihomeActivity.Failure();
            }
        });


    }
}
