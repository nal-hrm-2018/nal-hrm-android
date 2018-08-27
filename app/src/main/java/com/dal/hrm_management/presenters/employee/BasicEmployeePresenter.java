package com.dal.hrm_management.presenters.employee;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.views.employee.IBasicEmployeeFragment;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dal.hrm_management.presenters.login.LoginPresenter.token;

/**
 * Created by Luu Ngoc Lan on 16-Aug-18.
 */

public class BasicEmployeePresenter implements IBasicEmployeePresenter {

    private IBasicEmployeeFragment iBasicEmployeeFragment;

    public BasicEmployeePresenter(IBasicEmployeeFragment iBasicEmployeeFragment){
        this.iBasicEmployeeFragment = iBasicEmployeeFragment;
    }
    @Override
    public void getProfileEmployee(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ProfileResponse> call = apiService.getBasicInforEmployee(token,id);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                int statusCode = response.code();
                if (statusCode >= 300){
                    //get json failure
                    iBasicEmployeeFragment.getBasicEmployeeFailed();
                }else if (statusCode >= 200){
                    Profile profile = response.body().getProfile();
                    iBasicEmployeeFragment.getBasicEmployeeSuccess(profile);
                    Log.d("TAG", "success");
                }else{
                    iBasicEmployeeFragment.getBasicEmployeeFailed();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.d("failure", "here");
                iBasicEmployeeFragment.getBasicEmployeeFailed();
            }
        });
    }
}
