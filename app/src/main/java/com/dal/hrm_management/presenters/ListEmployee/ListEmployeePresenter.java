package com.dal.hrm_management.presenters.ListEmployee;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.ListEmployeeModel;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.list_employee.IListEmployee;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeePresenter implements IListEmployeePresenter{
    private IListEmployee iListEmployee;

    public ListEmployeePresenter(IListEmployee iListEmployeeActivity) {
        this.iListEmployee = iListEmployeeActivity;
    }

    @Override
    public void getListEmployee() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ListEmpResponse> call = apiService.getListEmployee(1,20,LoginPresenter.token);
        call.enqueue(new Callback<ListEmpResponse>() {
            @Override
            public void onResponse(Call<ListEmpResponse> call, Response<ListEmpResponse> response) {
                ListEmpResponse listEmpResponse = response.body();
                if (listEmpResponse.getResultCode() == 200) {
                    iListEmployee.Success(listEmpResponse.getData());

                }
            }

            @Override
            public void onFailure(Call<ListEmpResponse> call, Throwable t) {
                iListEmployee.getListEmployeeFailure();
            }
        });
    }

}
