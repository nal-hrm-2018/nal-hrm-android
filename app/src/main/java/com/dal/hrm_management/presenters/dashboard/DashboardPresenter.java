package com.dal.hrm_management.presenters.dashboard;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.eventInMonth.EventInMonthResponse;
import com.dal.hrm_management.models.listProjectEmpJoining.ListProjectJoiningResponse;
import com.dal.hrm_management.models.projectCompany.ProjectCompanyResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.dashboard.IDashboardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardPresenter implements IDashboardPresenter {
    private IDashboardFragment iDashboardFragment;

    public DashboardPresenter(IDashboardFragment iDashboardFragment) {
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

    @Override
    public void getProjectCompanyRunning() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProjectCompanyResponse> call = apiService.getRunningProjectCompany(LoginPresenter.token);
        call.enqueue(new Callback<ProjectCompanyResponse>() {
            @Override
            public void onResponse(Call<ProjectCompanyResponse> call, Response<ProjectCompanyResponse> response) {
                if (response.code() == 200) {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforProjectsCompanySuccess(response.body().getData());
                    }
                } else {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforProjectsCompanyFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<ProjectCompanyResponse> call, Throwable t) {
                if (iDashboardFragment != null) {
                    iDashboardFragment.getInforProjectsCompanyFailure();
                }
            }
        });
    }

    @Override
    public void getInforEventInThisMonth() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EventInMonthResponse> call = apiService.getInforEventInThisMonth(LoginPresenter.token);
        call.enqueue(new Callback<EventInMonthResponse>() {
            @Override
            public void onResponse(Call<EventInMonthResponse> call, Response<EventInMonthResponse> response) {
                if (response.code() == 200) {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforEventInThisMonthSuccess(response.body().getData());
                    }
                } else {
                    if (iDashboardFragment != null) {
                        iDashboardFragment.getInforEventInThisMonthFailure();
                    }
                }
            }

            @Override
            public void onFailure(Call<EventInMonthResponse> call, Throwable t) {
                if (iDashboardFragment != null) {
                    iDashboardFragment.getInforEventInThisMonthFailure();
                }
            }
        });
    }
}
