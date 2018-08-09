package com.dal.hrm_management.presenters.ListEmployee;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.LoginModel;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.list_employee.IListEmployeeActivity;

import java.util.ArrayList;
import java.util.List;

import okhttp3.Credentials;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListEmployeePresenter implements IListEmployeePresenter{
    private IListEmployeeActivity iListEmployeeActivity;

    public ListEmployeePresenter(IListEmployeeActivity iListEmployeeActivity) {
        this.iListEmployeeActivity = iListEmployeeActivity;
    }

    @Override
    public void getListEmployee() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        String auth = Credentials.basic("hrm_testing", "hrm_testing");



//        Call<ListEmployeeModel> call = apiService.getListEmployee(auth, LoginPresenter.token);
//        call.enqueue(new Callback<ListEmployeeModel>() {
//            @Override
//            public void onResponse(Call<ListEmployeeModel> call, Response<ListEmployeeModel> response) {
//                ListEmployeeModel listEmployeeModel= response.body();
//                if (listEmployeeModel.getResultCode() == "200"){
//                    List<ItemListEmployeeModel> list = listEmployeeModel.getData();
//                    Log.d("hihi",list.get(0).getEmail());
//                }else{
//                    Log.d("hihi",response.body().getResultCode());
//                    iListEmployeeActivity.getListEmployeeFailure();
//                }
//            }
//
//            @Override
//            public void onFailure(Call<ListEmployeeModel> call, Throwable t) {
//                iListEmployeeActivity.getListEmployeeFailure();
//            }
//        });
    }
}
