package com.dal.hrm_management.api;

import com.dal.hrm_management.models.listProjectEmpAttend.ListProjectEmpAttendResponse;
import com.dal.hrm_management.models.login.LoginModel;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.models.profile.ProfileResponse;

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
    Call<LoginModel> getToKen(@Header("Authorization") String token,
                              @Field("username") String email,
                              @Field("password") String password);
    @Headers({
            "X-Requested-With:XMLHttpRequest",
            "Content-Type:application/json"
    })
    @GET("api/profile/")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);

    @GET("api/list/employees")
    Call<ListEmpResponse> getListEmployee(@Query("page") int page,
                                          @Query("pageSize") int pageSize,
                                          @Header("Authorization") String token);

    @GET("api/basic")
    Call<ProfileResponse> getBasicInforEmployee(@Header("Authorization") String token, @Query("id") int id);

    @GET("api/project/employee")
    Call<ListProjectEmpAttendResponse> getProjectEmployeeAttend(@Query("id") int id,
                                                                @Header("Authorization") String token);


}
