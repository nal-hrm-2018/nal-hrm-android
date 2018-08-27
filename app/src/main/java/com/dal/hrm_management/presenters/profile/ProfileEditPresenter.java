package com.dal.hrm_management.presenters.profile;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.profile.Profile;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.views.profile.IProfileEditActivity;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 30-Jul-18.
 */

public class ProfileEditPresenter implements IProfilePresenter {
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

}
