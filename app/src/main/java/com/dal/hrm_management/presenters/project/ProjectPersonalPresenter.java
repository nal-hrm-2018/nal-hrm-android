package com.dal.hrm_management.presenters.project;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.listProjectEmpJoining.ListProjectJoiningResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.dashboard.IDashboardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProjectPersonalPresenter implements IProjectPersonalPresenter {
    private IDashboardFragment iDashboardFragment;

    public ProjectPersonalPresenter(IDashboardFragment iDashboardFragment) {
        this.iDashboardFragment = iDashboardFragment;
    }

    @Override
    public void getJoiningProject(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ListProjectJoiningResponse> call = apiService.getListProjectJoiningForOvertime(LoginPresenter.token);
        call.enqueue(new Callback<ListProjectJoiningResponse>() {
            @Override
            public void onResponse(Call<ListProjectJoiningResponse> call, Response<ListProjectJoiningResponse> response) {
                if (response.code() == 200) {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforJoiningProjectSuccess(response.body().getData());
                    }
                } else {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforJoiningProjectFailure();
                    }
                }

            }

            @Override
            public void onFailure(Call<ListProjectJoiningResponse> call, Throwable t) {
                if (iDashboardFragment != null) {
                    iDashboardFragment.getInforJoiningProjectFailure();
                }
            }
        });
    }
}
