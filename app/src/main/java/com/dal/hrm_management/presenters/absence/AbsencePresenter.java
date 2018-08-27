package com.dal.hrm_management.presenters.absence;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.absence.Absence;
import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.absence.IAbsenceViewActivity;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsencePresenter implements IAbsencePresenter {
    private IAbsenceViewActivity iAbsenceViewActivity;
    private List<Absence> arr = new ArrayList<>();


    public AbsencePresenter(IAbsenceViewActivity iAbsenceViewActivity) {
        this.iAbsenceViewActivity = iAbsenceViewActivity;
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
}
