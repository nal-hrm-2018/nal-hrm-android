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
    private IListProjectEmployeeAttendFragment iListProjectEmployeeAttendFragment;
    public static int total_project;
    public ListProjectEmpAttendPresenter(IListProjectEmployeeAttendFragment iListProjectEmployeeAttendFragment) {
        this.iListProjectEmployeeAttendFragment = iListProjectEmployeeAttendFragment;
    }


    @Override
    public void getListProjectEmployeeAttend(int id) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ListProjectEmpAttendResponse> call = apiService.getProjectEmployeeAttend(id, LoginPresenter.token);
        call.enqueue(new Callback<ListProjectEmpAttendResponse>() {
            @Override
            public void onResponse(Call<ListProjectEmpAttendResponse> call, Response<ListProjectEmpAttendResponse> response) {
                Log.d("Project","Success");
                ListProjectEmpAttendResponse listProjectEmpAttendResponse = response.body();
                if (listProjectEmpAttendResponse.getResultCode() == 200){
                    ArrayList<ProjectAndProcess> projectAndProcess = listProjectEmpAttendResponse.getData();
                    iListProjectEmployeeAttendFragment.Success(projectAndProcess);
                }else if (listProjectEmpAttendResponse.getResultCode() ==403){
                    iListProjectEmployeeAttendFragment.PermissionDeny();
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
