package com.dal.hrm_management.presenters.overtimePersonal;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.ProjectInProgressPOResponse;
import com.dal.hrm_management.models.overtimePersonal.DataOvertime;
import com.dal.hrm_management.models.overtimePersonal.OvertimePersonalResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.overtime.IOvertimeListFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OvertimePersonalPresenter implements IOvertimePersonalPresenter {
    private IOvertimeListFragment iOvertimeListFragment;

    public OvertimePersonalPresenter(IOvertimeListFragment iOvertimeListFragment) {
        this.iOvertimeListFragment = iOvertimeListFragment;
    }

    @Override
    public void getOvertimePersonal(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<OvertimePersonalResponse> call = apiService.getOvertimePersonal( LoginPresenter.token,currentPage,pageSize);
        call.enqueue(new Callback<OvertimePersonalResponse>() {
            @Override
            public void onResponse(Call<OvertimePersonalResponse> call, Response<OvertimePersonalResponse> response) {
                if (response.code() >=200 && response.code() < 300){
                    DataOvertime dataOvertime = response.body().getDataOvertime();
                    iOvertimeListFragment.getOvertimeSuccess(dataOvertime);
                }else{
                    iOvertimeListFragment.getOvertimeFailure();
                }
            }

            @Override
            public void onFailure(Call<OvertimePersonalResponse> call, Throwable t) {
                iOvertimeListFragment.getOvertimeFailure();
            }
        });
    }
}
