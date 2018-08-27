package com.dal.hrm_management.presenters.listProject;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.listProjectEmpAttend.ListProjectEmpAttendResponse;
import com.dal.hrm_management.models.listProjectEmpAttend.ProjectAndProcess;
import com.dal.hrm_management.presenters.home.HomePresenter;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.employee.IListProjectEmployeeAttendFragment;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Request;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ListProjectEmpAttendPresenter implements IListProjectEmpAttendPresenter {
    private static  final String TAG = ListProjectEmpAttendPresenter.class.getSimpleName();
    private IListProjectEmployeeAttendFragment iListProjectEmployeeAttendFragment;
    public static int total_project;
    public ListProjectEmpAttendPresenter(IListProjectEmployeeAttendFragment iListProjectEmployeeAttendFragment) {
        this.iListProjectEmployeeAttendFragment = iListProjectEmployeeAttendFragment;
    }


    @Override
    public void getListProjectEmployeeAttend(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ListProjectEmpAttendResponse> call = apiService.getProjectEmployeeAttend(id,1,20, LoginPresenter.token);
        call.enqueue(new Callback<ListProjectEmpAttendResponse>() {
            @Override
            public void onResponse(Call<ListProjectEmpAttendResponse> call, Response<ListProjectEmpAttendResponse> response) {
                Log.d(TAG+" response code :", String.valueOf(response.code()));
                if (response.code() >=300){
                    iListProjectEmployeeAttendFragment.Failure();
                }else if (response.code() >= 200){
                    ListProjectEmpAttendResponse listProjectEmpAttendResponse = response.body();
                    total_project = listProjectEmpAttendResponse.getData().getTotal();
                    ArrayList<ProjectAndProcess> projectAndProcess = listProjectEmpAttendResponse.getData().getList();
                    iListProjectEmployeeAttendFragment.Success(projectAndProcess);
                }else {
                    iListProjectEmployeeAttendFragment.Failure();
                }
            }

            @Override
            public void onFailure(Call<ListProjectEmpAttendResponse> call, Throwable t) {
                Log.d("Project","Failure");
                iListProjectEmployeeAttendFragment.Failure();
            }
        });
    }
}
