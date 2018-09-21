package com.dal.hrm_management.presenters.managerOvertime.hr;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.DataOvertime;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.ManageOvertimeHrResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageOvertime.hr.IOvertimeManageOfHrFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 19-Sep-18.
 */

public class ManageOvertimeOfHRPresenter implements IManageOvertimeOfHRPresenter {
    private IOvertimeManageOfHrFragment iOvertimeManageOfHrFragment;

    public ManageOvertimeOfHRPresenter(IOvertimeManageOfHrFragment iOvertimeManageOfHrFragment) {
        this.iOvertimeManageOfHrFragment = iOvertimeManageOfHrFragment;
    }

    @Override
    public void getListOvertime(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ManageOvertimeHrResponse> call = apiService.getOvertimeListOfHr( LoginPresenter.token,currentPage,pageSize);
        call.enqueue(new Callback<ManageOvertimeHrResponse>() {
            @Override
            public void onResponse(Call<ManageOvertimeHrResponse> call, Response<ManageOvertimeHrResponse> response) {
                if (response.code() >=200 && response.code() < 300){
                    DataOvertime dataOvertime = response.body().getData();
                    iOvertimeManageOfHrFragment.getOvertimeListSuccess(dataOvertime);
                }else{
                    iOvertimeManageOfHrFragment.getOvertimeListFailure();
                }
            }

            @Override
            public void onFailure(Call<ManageOvertimeHrResponse> call, Throwable t) {
                iOvertimeManageOfHrFragment.getOvertimeListFailure();
            }
        });
    }
}
