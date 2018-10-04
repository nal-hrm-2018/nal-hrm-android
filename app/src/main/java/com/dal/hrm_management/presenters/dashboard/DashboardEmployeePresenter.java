package com.dal.hrm_management.presenters.dashboard;


import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.dashboard.employee.DashboardEmployeeResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.dashboard.DashboardFragment;
import com.dal.hrm_management.views.dashboard.IDashboardFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardEmployeePresenter implements IDashboardEmployeePresenter {
    private IDashboardFragment iDashboardEmployee;
    public DashboardEmployeePresenter(DashboardFragment dashboardEmployee) {
        this.iDashboardEmployee = dashboardEmployee;
    }

    @Override
    public void getDashboardDEmployee() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<DashboardEmployeeResponse> call = apiService.getDashboardEmployee(LoginPresenter.token);
        call.enqueue(new Callback<DashboardEmployeeResponse>() {
            @Override
            public void onResponse(Call<DashboardEmployeeResponse> call, Response<DashboardEmployeeResponse> response) {
                if (response.code() == 200){
                    iDashboardEmployee.getDashboardEmployeeSuccess(response.body().getData());
                }else{
                    iDashboardEmployee.getDashboardEmployeeFailure();
                }
            }

            @Override
            public void onFailure(Call<DashboardEmployeeResponse> call, Throwable t) {
                iDashboardEmployee.getDashboardEmployeeFailure();
            }
        });
    }
}
