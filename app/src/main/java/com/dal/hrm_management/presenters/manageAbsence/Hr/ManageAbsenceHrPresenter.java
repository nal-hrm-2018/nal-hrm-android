package com.dal.hrm_management.presenters.manageAbsence.Hr;


import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageAbsence.hr.ListAbsenceForHr;
import com.dal.hrm_management.models.manageAbsence.hr.ManageAbsenceResponse;
import com.dal.hrm_management.models.manageAbsence.hr.editAbsence.EditAbsenceResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manage_absence.Hr.ListAbsence.IAbsenceHRFragment;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ManageAbsenceHrPresenter implements IManageAbsenceHrPresenter{
    private final String TAG = ManageAbsenceHrPresenter.class.getSimpleName();
    private IAbsenceHRFragment iAbsenceHRFragment;

    public ManageAbsenceHrPresenter(IAbsenceHRFragment iAbsenceHRFragment) {
        this.iAbsenceHRFragment = iAbsenceHRFragment;
    }

    @Override
    public void getListAbsence(int page,int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ManageAbsenceResponse> call = apiService.getListAbsenceForHr(page,pageSize, LoginPresenter.token);
        call.enqueue(new Callback<ManageAbsenceResponse>() {
            @Override
            public void onResponse(Call<ManageAbsenceResponse> call, Response<ManageAbsenceResponse> response) {
                Log.d(TAG + "-response code: ", String.valueOf(response.code()));
                if (response.code() >=200 && response.code() < 300){
                    int total = response.body().getData().getTotal();
                    List<ListAbsenceForHr> list = response.body().getData().getList();
                    iAbsenceHRFragment.getListAbsenceSuccess(total,list);
                }else{
                    iAbsenceHRFragment.getListAbsenceFailure();
                }
            }

            @Override
            public void onFailure(Call<ManageAbsenceResponse> call, Throwable t) {
                Log.d(TAG,"Failure get API");
                iAbsenceHRFragment.getListAbsenceFailure();
            }
        });
    }

    @Override
    public void deleteAbsence(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditAbsenceResponse> call = apiService.deleteAbsence(id,LoginPresenter.token);
        call.enqueue(new Callback<EditAbsenceResponse>() {
            @Override
            public void onResponse(Call<EditAbsenceResponse> call, Response<EditAbsenceResponse> response) {
                if (response.code() >=200 && response.code() < 300){
                    iAbsenceHRFragment.deleteAbsenceSuccess();
                }else{
                    iAbsenceHRFragment.deleteAbsenceFailure();
                }
            }

            @Override
            public void onFailure(Call<EditAbsenceResponse> call, Throwable t) {
                iAbsenceHRFragment.deleteAbsenceFailure();
            }
        });
    }
}
