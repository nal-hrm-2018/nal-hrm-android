package com.dal.hrm_management.api;

import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.models.listProjectEmpAttend.ListProjectEmpAttendResponse;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.models.profile.RolesResponse;
import com.dal.hrm_management.models.profile.TeamsResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginModel> getToKen(@Field("username") String email,
                              @Field("password") String password);
    @Headers({
            "X-Requested-With:XMLHttpRequest",
            "Content-Type:application/json"
    })
    @GET("api/profile/")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);

    @GET("api/employee/list")
    Call<ListEmpResponse> getListEmployee(@Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Header("Authorization") String token);

    @GET("api/employee/basic")
    Call<ProfileResponse> getBasicInforEmployee(@Header("Authorization") String token, @Query("id") int id);

    @GET("api/employee/project")
    Call<ListProjectEmpAttendResponse> getProjectEmployeeAttend(@Query("id") int id,
                                                                @Query("page") int page,
                                                                @Query("pageSize") int pageSize,
                                                                @Header("Authorization") String token);
    @GET("api/list/role")
    Call<RolesResponse> getRoles(@Header("Authorization") String token);

    @GET("api/list/team")
    Call<TeamsResponse> getTeams(@Header("Authorization") String token);

    @GET("api/employee/absence")
    Call<AbsencesResponse> getListAbsence(@Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Header("Authorization") String token);

}
