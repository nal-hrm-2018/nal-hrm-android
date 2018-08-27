package com.dal.hrm_management.presenters.absence;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.models.absence.addAbsence.AbsenceForm;
import com.dal.hrm_management.models.absence.addAbsence.AddAbsenceResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.absence.IAbsenceFormActivity;
import com.dal.hrm_management.views.absence.IAbsenceViewActivity;
import com.google.gson.JsonObject;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsencePresenter implements IAbsencePresenter {
    private IAbsenceViewActivity iAbsenceViewActivity;
    private IAbsenceFormActivity iAbsenceFormActivity;
    private List<Absence> arr = new ArrayList<>();
    private final String TAG = AbsencePresenter.class.getSimpleName();


    public AbsencePresenter(IAbsenceViewActivity iAbsenceViewActivity) {
        this.iAbsenceViewActivity = iAbsenceViewActivity;
    }
    public AbsencePresenter(IAbsenceFormActivity iAbsenceFormActivity){
        this.iAbsenceFormActivity = iAbsenceFormActivity;
    }

    @Override
    public void getDataAbsence(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AbsencesResponse> call = apiService.getListAbsence(currentPage, pageSize, LoginPresenter.token);
        call.enqueue(new Callback<AbsencesResponse>() {
            @Override
            public void onResponse(Call<AbsencesResponse> call, Response<AbsencesResponse> response) {
                if (response.code() >= 300) {
                    iAbsenceViewActivity.getAbsencePersonalFailed();
                } else if (response.code() >= 200) {
                    AbsencesResponse absencesResponse = response.body();
                    if (absencesResponse.getResultCode() == 200) {
                        iAbsenceViewActivity.getAbsencePersonalSuccess(absencesResponse.getDataAbsence());
                    }
                } else {
                    iAbsenceViewActivity.getAbsencePersonalFailed();
                }
            }

            @Override
            public void onFailure(Call<AbsencesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void addAbsence(String json) {
        Log.d(TAG,json);
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AddAbsenceResponse> call = apiService.addAbsence(LoginPresenter.token,json);
        call.enqueue(new Callback<AddAbsenceResponse>() {
            @Override
            public void onResponse(Call<AddAbsenceResponse> call, Response<AddAbsenceResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
            }

            @Override
            public void onFailure(Call<AddAbsenceResponse> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }


}
