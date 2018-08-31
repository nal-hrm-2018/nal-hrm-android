package com.dal.hrm_management.api;

import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.models.absence.addAbsence.AddAbsenceResponse;
import com.dal.hrm_management.models.absence.addAbsence.TypeAbsenceResponse;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.models.listProjectEmpAttend.ListProjectEmpAttendResponse;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.models.manageAbsence.hr.ManageAbsenceResponse;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.AbsenceEmployeeDetailResponse;
import com.dal.hrm_management.models.manageAbsence.hr.editAbsence.EditAbsenceResponse;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.models.profile.RolesResponse;
import com.dal.hrm_management.models.profile.TeamsResponse;

import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
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

    @GET("api/manage/employee/list")
    Call<ListEmpResponse> getListEmployee(@Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Header("Authorization") String token);


    @GET("api/manage/employee/basic")
    Call<ProfileResponse> getBasicInforEmployee(@Header("Authorization") String token, @Query("id") int id);

    @GET("api/manage/employee/project")
    Call<ListProjectEmpAttendResponse> getProjectEmployeeAttend(@Query("id") int id,
                                                                @Query("page") int page,
                                                                @Query("pageSize") int pageSize,
                                                                @Header("Authorization") String token);
    @GET("api/list/role")
    Call<RolesResponse> getRoles(@Header("Authorization") String token);

    @GET("api/list/team")
    Call<TeamsResponse> getTeams(@Header("Authorization") String token);


    @GET("api/absence")
    Call<AbsencesResponse> getListAbsence(@Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Header("Authorization") String token);
    @POST("/api/absence/add")
    Call<AddAbsenceResponse> addAbsence(@Header("Authorization") String token,
                                        @Body RequestBody json);
    @GET("/api/list/type/absence")
    Call<TypeAbsenceResponse> getTypeAbsence(@Header("Authorization") String token);

    /**
     * Get list absence of employee - all employee
     */
    @GET("/api/manage/absence/list")
    Call<ManageAbsenceResponse> getListAbsenceForHr(@Query("page") int page,
                                                    @Query("pageSize") int pageSize,
                                                    @Header("Authorization") String token);
    @GET("/api/manage/absence/employee")
    Call<AbsenceEmployeeDetailResponse> getDetailAbsenceEmployee(@Query("id") int id,
                                                                 @Query("page") int page,
                                                                 @Query("pageSize") int pageSize,
                                                                 @Header("Authorization") String token);

    /**
     * Edit absence with idAbsence
     * @param idAbsence
     * @param token
     * @param json
     * @return
     */

    @PUT("/api/manage/absence/edit/{absence_id}")
    Call<EditAbsenceResponse> putEditAbsence(@Path(value = "absence_id", encoded = true) int idAbsence,
                                             @Header("Authorization") String token,
                                             @Body RequestBody json);

    /**
     * Delete absence with idAbsence
     * @param idAbsence
     * @param token
     * @return
     */
    @DELETE("/api/manage/absence/delete/{absence_id}")
    Call<EditAbsenceResponse> deleteAbsence(@Path(value = "absence_id",encoded = true) int idAbsence,
                                            @Header("Authorization") String token);

}
