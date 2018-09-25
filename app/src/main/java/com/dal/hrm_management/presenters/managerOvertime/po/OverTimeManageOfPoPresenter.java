package com.dal.hrm_management.presenters.managerOvertime.po;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.ProjectInProgressPOResponse;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.ManageOvertimeHrResponse;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.OvertimePoResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageOvertime.po.IOTManageOfPOFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 06-Sep-18.
 */

public class OverTimeManageOfPoPresenter implements IOverTimeManageOfPoPresenter {
    private final String TAG = OverTimeManageOfPoPresenter.class.getSimpleName();
    private IOTManageOfPOFragment iotManageOfPOFragment;

    public OverTimeManageOfPoPresenter(IOTManageOfPOFragment iotManageOfPOFragment) {
        this.iotManageOfPOFragment = iotManageOfPOFragment;
    }

    @Override
    public void getListOverTimeForPO(String idProject, int page, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<OvertimePoResponse> call = apiService.getOverTimeListForPO(LoginPresenter.token, idProject, page, pageSize);
        call.enqueue(new Callback<OvertimePoResponse>() {
            @Override
            public void onResponse(Call<OvertimePoResponse> call, Response<OvertimePoResponse> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    Log.d(TAG, "getOvertimeListForPo Success " + response.code());
                    iotManageOfPOFragment.getListOTSucess(response.body().getData());
                } else {
                    Log.d(TAG, "getOvertimeListForPo Failure " + response.code());
                    iotManageOfPOFragment.getListOTFailure();
                }
            }

            @Override
            public void onFailure(Call<OvertimePoResponse> call, Throwable t) {
                iotManageOfPOFragment.getlistOTError(t);
            }
        });
    }

    @Override
    public void getListProjectInProgressOfPO(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProjectInProgressPOResponse> call = apiService.getProjectInProgressOfPO(currentPage, pageSize, LoginPresenter.token);
        call.enqueue(new Callback<ProjectInProgressPOResponse>() {
            @Override
            public void onResponse(Call<ProjectInProgressPOResponse> call, Response<ProjectInProgressPOResponse> response) {
                if (response.code() >= 200 && response.code() < 300) {
                    DataProject dataProjects = response.body().getData();
                    iotManageOfPOFragment.getListProjectInProgressSuccess(dataProjects);
                } else {
                    iotManageOfPOFragment.getListProjectInProgressFailed();
                }
            }

            @Override
            public void onFailure(Call<ProjectInProgressPOResponse> call, Throwable t) {
                iotManageOfPOFragment.getListProjectInProgressFailed();
            }
        });
    }
}
