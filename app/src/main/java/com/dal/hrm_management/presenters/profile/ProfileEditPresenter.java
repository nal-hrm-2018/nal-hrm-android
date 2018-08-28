package com.dal.hrm_management.presenters.profile;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.models.profile.Team;
import com.dal.hrm_management.models.profile.TeamsResponse;
import com.dal.hrm_management.views.profile.IProfileEditActivity;

import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 30-Jul-18.
 */

public class ProfileEditPresenter implements IProfileEditPresenter {
    private IProfileEditActivity iProfileEditActivity;
    private final String TAG = ProfileEditPresenter.class.getSimpleName();
    public ProfileEditPresenter(IProfileEditActivity iProfileEditActivity) {
        this.iProfileEditActivity = iProfileEditActivity;
    }

    @Override
    public void getProfile(String token) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<ProfileResponse> call = apiService.getProfile(token);
        call.enqueue(new Callback<ProfileResponse>() {
            @Override
            public void onResponse(Call<ProfileResponse> call, Response<ProfileResponse> response) {
                Log.d(TAG + " response code: ", String.valueOf(response.code()));
                if(response.code() >=300){
                    iProfileEditActivity.getProfileFailure();
                }else if (response.code() >= 200){
                    Profile profile = response.body().getProfile();
                    iProfileEditActivity.getProfileSuccess(profile);
                }else {
                    iProfileEditActivity.getProfileFailure();
                }
            }

            @Override
            public void onFailure(Call<ProfileResponse> call, Throwable t) {
                Log.d("failure", "here");
                iProfileEditActivity.getProfileFailure();
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
                    iProfileEditActivity.getTeamsFailed();
                }else if (response.code() >=200){
                    List<Team> teams = response.body().getTeams();
                    iProfileEditActivity.getTeamsSuccess(teams);
                }else{
                    iProfileEditActivity.getTeamsFailed();
                }
            }

            @Override
            public void onFailure(Call<TeamsResponse> call, Throwable t) {
                iProfileEditActivity.getTeamsFailed();
            }
        });
    }

}
