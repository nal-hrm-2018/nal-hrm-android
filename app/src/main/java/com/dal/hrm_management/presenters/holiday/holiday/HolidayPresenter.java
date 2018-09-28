package com.dal.hrm_management.presenters.holiday.holiday;


import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.holiday.HolidayResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageAbsence.Hr.holiday.HolidayHRFragment;
import com.dal.hrm_management.views.manageAbsence.Hr.holiday.IHolidayFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayPresenter implements IHolidayPresenter{
    IHolidayFragment iHolidayFragment;
    public HolidayPresenter(IHolidayFragment holidayHRFragment) {
        this.iHolidayFragment = holidayHRFragment;
    }

    @Override
    public void getHoliday() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HolidayResponse> call = apiService.getListHoliday(LoginPresenter.token);
        call.enqueue(new Callback<HolidayResponse>() {
            @Override
            public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                if (response.code() ==200){
                    iHolidayFragment.getHolidaySuccess(response.body().getData());
                }
            }

            @Override
            public void onFailure(Call<HolidayResponse> call, Throwable t) {
                iHolidayFragment.getHolidayFailure();
            }
        });
    }
}
