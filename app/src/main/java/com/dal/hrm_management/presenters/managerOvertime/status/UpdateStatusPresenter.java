package com.dal.hrm_management.presenters.managerOvertime.status;


import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageOvertime.status.UpdateStatusResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manageOvertime.hr.IOvertimeManageOfHrFragment;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UpdateStatusPresenter implements IUpdateStatusPresenter {
    private IOvertimeManageOfHrFragment iOvertimeManageOfHrFragment;

    public UpdateStatusPresenter(IOvertimeManageOfHrFragment iOvertimeManageOfHrFragment) {
        this.iOvertimeManageOfHrFragment = iOvertimeManageOfHrFragment;
    }

    @Override
    public void updateStatusOvertime(Long idOvertime, RequestBody body) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<UpdateStatusResponse> call = apiService.putStatusOvertime(idOvertime, LoginPresenter.token, body);
        call.enqueue(new Callback<UpdateStatusResponse>() {
            @Override
            public void onResponse(Call<UpdateStatusResponse> call, Response<UpdateStatusResponse> response) {
                if (response.code() == 200) {
                    iOvertimeManageOfHrFragment.updateStatusSuccess(response.body().getData());
                } else if (response.code() > 200) {
                    iOvertimeManageOfHrFragment.updateStatusFailure();
                }

            }

            @Override
            public void onFailure(Call<UpdateStatusResponse> call, Throwable t) {
                iOvertimeManageOfHrFragment.updateStatusFailure();
            }
        });
    }
}
