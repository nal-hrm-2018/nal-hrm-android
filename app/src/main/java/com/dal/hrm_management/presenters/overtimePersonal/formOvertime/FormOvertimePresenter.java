package com.dal.hrm_management.presenters.overtimePersonal.formOvertime;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.listProjectEmpJoining.ListProjectJoiningResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;
import com.dal.hrm_management.views.overtime.formOvertime.IFormOvertimeActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FormOvertimePresenter implements IFormOvertimePresenter {
    private final String TAG = FormOvertimePresenter.class.getSimpleName();
    IFormOvertimeActivity formOvertimeActivity;
    public FormOvertimePresenter(IFormOvertimeActivity formOvertime) {
        this.formOvertimeActivity = formOvertime;
    }

    @Override
    public void getListProject() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ListProjectJoiningResponse>call = apiService.getListProjectJoiningForOvertime(LoginPresenter.token);
        call.enqueue(new Callback<ListProjectJoiningResponse>() {
            @Override
            public void onResponse(Call<ListProjectJoiningResponse> call, Response<ListProjectJoiningResponse> response) {
                if (response.code() ==200){
                    formOvertimeActivity.getListProjectSuccess(response.body().getData());
                }else{
                    formOvertimeActivity.getListProjectFailure();
                }

            }

            @Override
            public void onFailure(Call<ListProjectJoiningResponse> call, Throwable t) {
                Log.d(TAG, "get project failure : "+ t.getLocalizedMessage());
                formOvertimeActivity.getListProjectFailure();
            }
        });
    }
}
