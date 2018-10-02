package com.dal.hrm_management.presenters.holiday.holiday;


import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.holiday.HolidayResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageAbsence.Hr.holiday.IHolidayFragment;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;
import com.dal.hrm_management.views.overtime.formOvertime.IFormOvertimeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HolidayPresenter implements IHolidayPresenter{
    private final String TAG = HolidayPresenter.class.getSimpleName();
    IHolidayFragment iHolidayFragment;
    IFormOvertimeActivity formOvertimeActivity;
    public HolidayPresenter(IHolidayFragment holidayHRFragment) {
        this.iHolidayFragment = holidayHRFragment;
    }

    public HolidayPresenter(FormOvertimeActivity formOvertimeActivity) {
        this.formOvertimeActivity = formOvertimeActivity;
    }

    @Override
    public void getHoliday() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<HolidayResponse> call = apiService.getListHoliday(LoginPresenter.token);
        call.enqueue(new Callback<HolidayResponse>() {
            @Override
            public void onResponse(Call<HolidayResponse> call, Response<HolidayResponse> response) {
                if (response.code() ==200){
                    Log.d(TAG,"get holiday success");
                    if (iHolidayFragment !=null) {
                        iHolidayFragment.getHolidaySuccess(response.body().getData());
                    }else if (formOvertimeActivity != null){
                        formOvertimeActivity.getHolidaySuccess(response.body().getData());
                    }
                }
            }

            @Override
            public void onFailure(Call<HolidayResponse> call, Throwable t) {
                iHolidayFragment.getHolidayFailure();
            }
        });
    }
}
