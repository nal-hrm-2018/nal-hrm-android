package com.dal.hrm_management.api;

import com.dal.hrm_management.models.LoginModel;
import com.dal.hrm_management.models.profile.ProfileResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginModel> getToKen(@Header("Authorization") String auth,
                              @Field("email") String email,
                              @Field("password") String password);

    @GET("api/profile/show")
    Call<ProfileResponse> getProfile(@Header("Authorization") String auth,
                                     @Header("token") String token);
}
