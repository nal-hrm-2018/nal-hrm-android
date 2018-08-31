package com.dal.hrm_management.presenters.absence;

import android.util.Log;

import com.dal.hrm_management.api.ApiClient;
import com.dal.hrm_management.api.ApiInterface;
import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.models.absence.addAbsence.AddAbsenceResponse;
import com.dal.hrm_management.models.absence.addAbsence.TypeAbsenceResponse;
import com.dal.hrm_management.models.manageAbsence.hr.editAbsence.EditAbsenceResponse;
import com.dal.hrm_management.presenters.login.LoginPresenter;
import com.dal.hrm_management.views.absence.IAbsenceFormActivity;
import com.dal.hrm_management.views.absence.IAbsenceViewActivity;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AbsencePresenter implements IAbsencePresenter {
    private IAbsenceViewActivity iAbsenceViewActivity;
    private IAbsenceFormActivity iAbsenceFormActivity;
    private final String TAG = AbsencePresenter.class.getSimpleName();


    public AbsencePresenter(IAbsenceViewActivity iAbsenceViewActivity) {
        this.iAbsenceViewActivity = iAbsenceViewActivity;
    }
    public AbsencePresenter(IAbsenceFormActivity iAbsenceFormActivity){
        this.iAbsenceFormActivity = iAbsenceFormActivity;
    }

    @Override
    public void getDataAbsence(int currentPage, int pageSize) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AbsencesResponse> call = apiService.getListAbsence(currentPage, pageSize, LoginPresenter.token);
        call.enqueue(new Callback<AbsencesResponse>() {
            @Override
            public void onResponse(Call<AbsencesResponse> call, Response<AbsencesResponse> response) {
                if (response.code() >= 300) {
                    iAbsenceViewActivity.getAbsencePersonalFailed();
                } else if (response.code() >= 200) {
                    AbsencesResponse absencesResponse = response.body();
                    if (absencesResponse.getResultCode() == 200) {
                        iAbsenceViewActivity.getAbsencePersonalSuccess(absencesResponse.getDataAbsence());
                    }
                } else {
                    iAbsenceViewActivity.getAbsencePersonalFailed();
                }
            }

            @Override
            public void onFailure(Call<AbsencesResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void addAbsence(RequestBody json) {
        Log.d(TAG,json.toString());
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<AddAbsenceResponse> call = apiService.addAbsence(LoginPresenter.token,json);
        call.enqueue(new Callback<AddAbsenceResponse>() {
            @Override
            public void onResponse(Call<AddAbsenceResponse> call, Response<AddAbsenceResponse> response) {
                Log.d(TAG, String.valueOf(response.code()));
                if (response.code() >= 300){
                    iAbsenceFormActivity.Failure();
                }else if (response.code() >=200){
                    iAbsenceFormActivity.Success();
                }else{
                    iAbsenceFormActivity.Failure();
                }
            }

            @Override
            public void onFailure(Call<AddAbsenceResponse> call, Throwable t) {
                Log.d(TAG, "failure");
            }
        });
    }

    @Override
    public void getTypeAbsence() {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<TypeAbsenceResponse> call = apiService.getTypeAbsence(LoginPresenter.token);
        call.enqueue(new Callback<TypeAbsenceResponse>() {
            @Override
            public void onResponse(Call<TypeAbsenceResponse> call, Response<TypeAbsenceResponse> response) {
                if (response.code()>=200 || response.code() < 300)
                {
                    iAbsenceFormActivity.loadTypeAbsenceSuccess(response.body().getData());

                }else{
                    iAbsenceFormActivity.loadTypeAbsenceFailure();
                }
            }

            @Override
            public void onFailure(Call<TypeAbsenceResponse> call, Throwable t) {
                iAbsenceFormActivity.loadTypeAbsenceFailure();
            }
        });
    }

    @Override
    public void editAbsence(RequestBody json, int idAbsence) {
        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);
        Call<EditAbsenceResponse> call = apiService.putEditAbsence(idAbsence,LoginPresenter.token,json);
        call.enqueue(new Callback<EditAbsenceResponse>() {
            @Override
            public void onResponse(Call<EditAbsenceResponse> call, Response<EditAbsenceResponse> response) {
                Log.d(TAG,"response code :" +response.code());
                if (response.code() >=200 && response.code() < 300){
                    iAbsenceFormActivity.EditAbsenceSuccess();
                }else{
                    iAbsenceFormActivity.EditAbsenceFailure();
                }
            }

            @Override
            public void onFailure(Call<EditAbsenceResponse> call, Throwable t) {
                iAbsenceFormActivity.EditAbsenceFailure();
            }
        });

    }


}
