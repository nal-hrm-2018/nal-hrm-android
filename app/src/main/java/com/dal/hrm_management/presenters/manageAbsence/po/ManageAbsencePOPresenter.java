package com.dal.hrm_management.presenters.manageAbsence.po;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.manageAbsence.po.listAbsence.AbsenceInProjectOfPOResponse;
import com.dal.hrm_management.models.manageAbsence.po.listAbsence.DataAbsence;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.DataProject;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.ProjectInProgressPOResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.manage_absence.po.IAbsenceManagerForPOFragment;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by Luu Ngoc Lan on 31-Aug-18.
 */

public class ManageAbsencePOPresenter implements IManageAbsencePOPresenter {

    private final String TAG = ManageAbsencePOPresenter.class.getSimpleName();
    private IAbsenceManagerForPOFragment iAbsenceManagerForPOFragment;

    public ManageAbsencePOPresenter(IAbsenceManagerForPOFragment iAbsenceManagerForPOFragment) {
        this.iAbsenceManagerForPOFragment = iAbsenceManagerForPOFragment;
    }


    @Override
    public void getListProject(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<ProjectInProgressPOResponse> call = apiService.getProjectInProgressOfPO(currentPage,pageSize, LoginPresenter.token);
        call.enqueue(new Callback<ProjectInProgressPOResponse>() {
            @Override
            public void onResponse(Call<ProjectInProgressPOResponse> call, Response<ProjectInProgressPOResponse> response) {
                if (response.code() >=200 && response.code() < 300){
                    DataProject dataProjects = response.body().getData();
                    iAbsenceManagerForPOFragment.getListProjectInProgressSuccess(dataProjects);
                }else{
                    iAbsenceManagerForPOFragment.getListProjectInProgressFailed();
                }
            }

            @Override
            public void onFailure(Call<ProjectInProgressPOResponse> call, Throwable t) {
                iAbsenceManagerForPOFragment.getListProjectInProgressFailed();
            }
        });
    }

    @Override
    public void getListAbsence(String idProject) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AbsenceInProjectOfPOResponse> call = apiService.getAllAbsenceInProject(idProject, LoginPresenter.token);
        call.enqueue(new Callback<AbsenceInProjectOfPOResponse>() {
            @Override
            public void onResponse(Call<AbsenceInProjectOfPOResponse> call, Response<AbsenceInProjectOfPOResponse> response) {
                if (response.code() >=200 && response.code() < 300){
                    int total = response.body().getData().getTotal();
                    DataAbsence dataAbsence = response.body().getData();
                    iAbsenceManagerForPOFragment.getListAbsenceInProjectSuccess(dataAbsence);
                }else{
                    iAbsenceManagerForPOFragment.getListAbsenceInProjectFailed();
                }
            }

            @Override
            public void onFailure(Call<AbsenceInProjectOfPOResponse> call, Throwable t) {
                iAbsenceManagerForPOFragment.getListAbsenceInProjectFailed();
            }
        });
    }
}
