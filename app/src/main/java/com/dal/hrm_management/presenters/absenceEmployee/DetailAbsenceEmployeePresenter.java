package com.dal.hrm_management.presenters.absenceEmployee;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.fakeData.AbsenceModel;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.AbsenceEmployeeDetailResponse;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.Data;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.absenceEmployee.IDetailAbsenceEmployeeActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 21-Aug-18.
 */

public class DetailAbsenceEmployeePresenter implements IDetailAbsenceEmployeePresenter {
    private IDetailAbsenceEmployeeActivity iDetailAbsenceEmployeeActivity;
    public List<AbsenceModel> arr = new ArrayList<>();


    public DetailAbsenceEmployeePresenter(IDetailAbsenceEmployeeActivity iDetailAbsenceEmployeeActivity) {
        this.iDetailAbsenceEmployeeActivity = iDetailAbsenceEmployeeActivity;
    }

    @Override
    public void getDetailAbsenceEmployee(int id,int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");
        Call<AbsenceEmployeeDetailResponse> call = apiService.getDetailAbsenceEmployee(id,currentPage,pageSize, LoginPresenter.token);
        call.enqueue(new Callback<AbsenceEmployeeDetailResponse>() {
            @Override
            public void onResponse(Call<AbsenceEmployeeDetailResponse> call, Response<AbsenceEmployeeDetailResponse> response) {
                if (response.code() >=300){
                    iDetailAbsenceEmployeeActivity.getDetailAbsenceEmployeeFailed();
                }else if (response.code() >=200){
                    Data dataAbsence = response.body().getData();
                    iDetailAbsenceEmployeeActivity.getDetailAbsenceEmployeeSuccess(dataAbsence);
                }else{
                    iDetailAbsenceEmployeeActivity.getDetailAbsenceEmployeeFailed();
                }
            }

            @Override
            public void onFailure(Call<AbsenceEmployeeDetailResponse> call, Throwable t) {
                iDetailAbsenceEmployeeActivity.getDetailAbsenceEmployeeFailed();
            }
        });
    }
}
