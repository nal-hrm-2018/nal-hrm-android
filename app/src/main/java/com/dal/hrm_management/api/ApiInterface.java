package com.dal.hrm_management.api;

import com.dal.hrm_management.models.absence.AbsencesResponse;
import com.dal.hrm_management.models.absence.addAbsence.AddAbsenceResponse;
import com.dal.hrm_management.models.absence.addAbsence.TypeAbsenceResponse;

import com.dal.hrm_management.models.dashboard.employee.DashboardEmployeeResponse;

import com.dal.hrm_management.models.dashboard.eventInMonth.EventInMonthResponse;
import com.dal.hrm_management.models.dashboard.notification.DashboardNotificationResponse;
import com.dal.hrm_management.models.holiday.HolidayResponse;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.models.listProjectEmpAttend.ListProjectEmpAttendResponse;
import com.dal.hrm_management.models.listProjectEmpJoining.ListProjectJoiningResponse;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.models.manageAbsence.hr.ManageAbsenceResponse;
import com.dal.hrm_management.models.manageAbsence.hr.absenceEmployee.AbsenceEmployeeDetailResponse;
import com.dal.hrm_management.models.manageAbsence.po.listAbsence.AbsenceInProjectOfPOResponse;
import com.dal.hrm_management.models.manageAbsence.po.listProjectInProgress.ProjectInProgressPOResponse;
import com.dal.hrm_management.models.manageAbsence.hr.editAbsence.EditAbsenceResponse;
import com.dal.hrm_management.models.manageOvertime.hr.viewList.ManageOvertimeHrResponse;
import com.dal.hrm_management.models.manageOvertime.po.viewlist.OvertimePoResponse;
import com.dal.hrm_management.models.manageOvertime.status.UpdateStatusResponse;
import com.dal.hrm_management.models.overtimePersonal.AddEditOvertime.AddOvertimeResponse;
import com.dal.hrm_management.models.overtimePersonal.AddEditOvertime.EditOvertimeResponse;
import com.dal.hrm_management.models.overtimePersonal.OvertimePersonalResponse;
import com.dal.hrm_management.models.profile.ProfileResponse;
import com.dal.hrm_management.models.profile.RolesResponse;
import com.dal.hrm_management.models.profile.TeamsResponse;
import com.dal.hrm_management.models.projectCompany.ProjectCompanyResponse;

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

    @POST("api/absence/add")
    Call<AddAbsenceResponse> addAbsence(@Header("Authorization") String token,
                                        @Body RequestBody json);

    @GET("api/list/type/absence")
    Call<TypeAbsenceResponse> getTypeAbsence(@Header("Authorization") String token);

    /**
     * Get list absence of employee - all employee
     */
    @GET("api/manage/absence/list")
    Call<ManageAbsenceResponse> getListAbsenceForHr(@Query("page") int page,
                                                    @Query("pageSize") int pageSize,
                                                    @Header("Authorization") String token);

    @GET("api/manage/absence/search")
    Call<ManageAbsenceResponse> searchAbsenceInMonth(@Query("month") int month,
                                                     @Query("year") int year,
                                                     @Query("page") int page,
                                                     @Query("pageSize") int pageSize,
                                                     @Header("Authorization") String token);

    @GET("api/manage/absence/employee")
    Call<AbsenceEmployeeDetailResponse> getDetailAbsenceEmployee(@Query("id") int id,
                                                                 @Query("page") int page,
                                                                 @Query("pageSize") int pageSize,
                                                                 @Header("Authorization") String token);

    @GET("api/manage/absence/po")
    Call<ProjectInProgressPOResponse> getProjectInProgressOfPO(@Query("page") int page,
                                                               @Query("pageSize") int pageSize,
                                                               @Header("Authorization") String token);

    @GET("api/manage/absence/po/project")
    Call<AbsenceInProjectOfPOResponse> getAllAbsenceInProject(@Query("id") String idProject,
                                                              @Header("Authorization") String token);

    /**
     * Edit absence with idAbsence
     *
     * @param idAbsence
     * @param token
     * @param json
     * @return
     */

    @PUT("api/manage/absence/edit/{absence_id}")
    Call<EditAbsenceResponse> putEditAbsence(@Path(value = "absence_id", encoded = true) int idAbsence,
                                             @Header("Authorization") String token,
                                             @Body RequestBody json);

    /**
     * Delete absence with idAbsence
     *
     * @param idAbsence
     * @param token
     * @return
     */
    @DELETE("api/manage/absence/delete/{absence_id}")
    Call<EditAbsenceResponse> deleteAbsence(@Path(value = "absence_id", encoded = true) int idAbsence,
                                            @Header("Authorization") String token);

    @GET("api/overtime")
    Call<OvertimePersonalResponse> getOvertimePersonal(@Header("Authorization") String token,
                                                       @Query("page") int page,
                                                       @Query("pageSize") int pageSize);

    @GET("api/manage/overtime/hr/list")
    Call<ManageOvertimeHrResponse> getOvertimeListOfHr(@Header("Authorization") String token,
                                                       @Query("page") int page,
                                                       @Query("pageSize") int pageSize);

    /**
     * Get list overtime list for hr
     *
     * @param token
     * @param idProject
     * @param page
     * @param pageSize
     * @return
     */
    @GET("api/manage/overtime/po/list")
    Call<OvertimePoResponse> getOverTimeListForPO(@Header("Authorization") String token,
                                                  @Query("id") String idProject,
                                                  @Query("page") int page,
                                                  @Query("pageSize") int pageSize);

    /**
     * list project emp joining
     *
     * @param token
     * @return
     */
    @GET("api/list/project/joining")
    Call<ListProjectJoiningResponse> getListProjectJoiningForOvertime(@Header("Authorization") String token);


    @POST("api/overtime/add")
    Call<AddOvertimeResponse> addOvertime(@Header("Authorization") String token,
                                          @Body RequestBody json);

    /**
     * Edit overtime with idAbsence
     *
     * @param idOvertime
     * @param token
     * @param json
     * @return
     */

    @PUT("api/overtime/edit/{overtime_id}")
    Call<EditOvertimeResponse> putEditOvertime(@Path(value = "overtime_id", encoded = true) Long idOvertime,
                                               @Header("Authorization") String token,
                                               @Body RequestBody json);

    /**
     * @param idOvertime
     * @param token
     * @param json
     * @return
     */
    @PUT("/api/manage/overtime/confirm/{overtime_id}")
    Call<UpdateStatusResponse> putStatusOvertime(@Path(value = "overtime_id", encoded = true) Long idOvertime,
                                                 @Header("Authorization") String token,
                                                 @Body RequestBody json);

    @GET("api/holiday")
    Call<HolidayResponse> getListHoliday(@Header("Authorization") String token);


    @GET("api/bo/dashboard/employee")
    Call<DashboardEmployeeResponse> getDashboardEmployee(@Header("Authorization") String token);
    /**
     * Get all projects running of company, role=PO
     *
     * @param token
     * @return
     */
    @GET("api/dashboard/project-company")
    Call<ProjectCompanyResponse> getRunningProjectCompany(@Header("Authorization") String token);


    /**
     * Get infor event in month, role=BO
     *
     * @param token
     * @return
     */
    @GET("api/bo/dashboard/event")
    Call<EventInMonthResponse> getInforEventInThisMonth(@Header("Authorization") String token);

    @GET("api/dashboard/notification")
    Call<DashboardNotificationResponse> getNotification(@Header("Authorization") String token);
}
