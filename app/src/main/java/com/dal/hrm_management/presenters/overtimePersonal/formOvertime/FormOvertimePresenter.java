package com.dal.hrm_management.presenters.overtimePersonal.formOvertime;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.listProjectEmpJoining.ListProjectJoiningResponse;
import com.dal.hrm_management.models.overtimePersonal.AddEditOvertime.AddOvertimeResponse;
import com.dal.hrm_management.models.overtimePersonal.AddEditOvertime.EditOvertimeResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.overtime.formOvertime.FormOvertimeActivity;
import com.dal.hrm_management.views.overtime.formOvertime.IFormOvertimeActivity;

import okhttp3.RequestBody;
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
        Call<ListProjectJoiningResponse> call = apiService.getListProjectJoiningForOvertime(LoginPresenter.token);
        call.enqueue(new Callback<ListProjectJoiningResponse>() {
            @Override
            public void onResponse(Call<ListProjectJoiningResponse> call, Response<ListProjectJoiningResponse> response) {
                if (response.code() == 200) {
                    formOvertimeActivity.getListProjectSuccess(response.body().getData());
                } else {
                    formOvertimeActivity.getListProjectFailure();
                }

            }

            @Override
            public void onFailure(Call<ListProjectJoiningResponse> call, Throwable t) {
                Log.d(TAG, "get project failure : " + t.getLocalizedMessage());
                formOvertimeActivity.getListProjectFailure();
            }
        });
    }

    @Override
    public void addOvertime(RequestBody body) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AddOvertimeResponse> call = apiService.addOvertime(LoginPresenter.token, body);
        call.enqueue(new Callback<AddOvertimeResponse>() {
            @Override
            public void onResponse(Call<AddOvertimeResponse> call, Response<AddOvertimeResponse> response) {
                if (response.code() == 200) {
                    formOvertimeActivity.addOvertimeSuccess();
                } else if (response.code() == 400) {
                    formOvertimeActivity.addOvertimeFailure("Some thing error");
                }

            }

            @Override
            public void onFailure(Call<AddOvertimeResponse> call, Throwable t) {
                formOvertimeActivity.addOvertimeFailure(t.getMessage());
            }
        });
    }

    @Override
    public void editOvertime(RequestBody body, Long idOvertime) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditOvertimeResponse> call = apiService.putEditOvertime(idOvertime, LoginPresenter.token, body);
        call.enqueue(new Callback<EditOvertimeResponse>() {
            @Override
            public void onResponse(Call<EditOvertimeResponse> call, Response<EditOvertimeResponse> response) {
                Log.d(TAG, "response code :" + response.code());
                if (response.code() >= 200 && response.code() < 300) {
                    String msg = response.body().getData();
                    formOvertimeActivity.editOvertimeSuccess(msg);
                } else {
                    formOvertimeActivity.editOvertimeFailure();
                }
            }

            @Override
            public void onFailure(Call<EditOvertimeResponse> call, Throwable t) {
                formOvertimeActivity.editOvertimeFailure();
            }
        });
    }
}
