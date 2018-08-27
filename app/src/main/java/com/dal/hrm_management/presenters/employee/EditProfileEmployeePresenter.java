package com.dal.hrm_management.presenters.employee;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.models.profile.TeamsResponse;
import com.dal.hrm_management.views.employee.IEditProfileEmployeeActivity;

import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.dal.hrm_management.presenters.login.LoginPresenter.token;

/**
 * Created by Luu Ngoc Lan on 23-Aug-18.
 */

public class EditProfileEmployeePresenter implements IEditProfileEmployeePresenter {
    private IEditProfileEmployeeActivity iEditProfileEmployeeActivity;
    private final String TAG = EditProfileEmployeePresenter.class.getSimpleName();
    public EditProfileEmployeePresenter(IEditProfileEmployeeActivity iEditProfileEmployeeActivity) {
        this.iEditProfileEmployeeActivity = iEditProfileEmployeeActivity;
    }

    @Override
    public void getProfileEmployee(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ProfileResponse> call = apiService.getBasicInforEmployee(token, id);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                Log.d(TAG + " response code : ", String.valueOf(response.code()));
                if (response.code() >=300){
                    iEditProfileEmployeeActivity.getBasicEmployeeFailed();
                }else if (response.code() >=200){
                    Profile profile = response.body().getProfile();
                    iEditProfileEmployeeActivity.getBasicEmployeeSuccess(profile);
                }else{
                    iEditProfileEmployeeActivity.getBasicEmployeeFailed();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                iEditProfileEmployeeActivity.getBasicEmployeeFailed();
            }
        });
    }

    @Override
    public void getTeams(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<TeamsResponse> call = apiService.getTeams(token);
        call.enqueue(new Callback<TeamsResponse>() {
            @Override
            public void onResponse(Call<TeamsResponse> call, Response<TeamsResponse> response) {
                Log.d(TAG + " response code :", String.valueOf(response.code()));
                if (response.code() >=300){
                    iEditProfileEmployeeActivity.getTeamsFailed();
                }else if (response.code() >=200){
                    List<Team> teams = response.body().getTeams();
                    iEditProfileEmployeeActivity.getTeamsSuccess(teams);
                }else{
                    iEditProfileEmployeeActivity.getTeamsFailed();
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                iEditProfileEmployeeActivity.getTeamsFailed();
            }
        });
    }
}
