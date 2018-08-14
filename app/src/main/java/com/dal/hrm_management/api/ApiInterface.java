package com.dal.hrm_management.api;

import com.dal.hrm_management.models.LoginModel;
import com.dal.hrm_management.models.listEmployee.ListEmpResponse;
import com.dal.hrm_management.models.profile.Profile;
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
    Call<LoginModel> getToKen(@Header("Authorization") String auth,
                              @Field("username") String email,
                              @Field("password") String password);
    @Headers({
            "X-Requested-With:XMLHttpRequest",
            "Content-Type:application/json"
    })
//    @GET("api/employee")
//    Call<ListEmployeeModel> getListEmployee(@Header("Authorization") String auth,
//                                            @Header("token") String token);

//    @GET("api/profile/")
//    Call<ProfileResponse> getProfile(@Header("Authorization") String auth,
//                                     @Header("token") String token);
    @GET("api/profile/")
    Call<ProfileResponse> getProfile(@Header("Authorization") String token);

    @GET("api/list/employees?page=2&pageSize=20")
    Call<ListEmpResponse> getListEmployee(@Header("Authorization") String token);

    @GET("api/basic")
    Call<Profile> getBasicInforEmployee(@Query("id") int id);

}
