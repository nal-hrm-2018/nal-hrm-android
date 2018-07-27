package com.dal.hrm_management.api;

import com.dal.hrm_management.models.LoginModel;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.Header;
import retrofit2.http.POST;

public interface ApiInterface {
    @FormUrlEncoded
    @POST("api/login")
    Call<LoginModel> getToKen(@Header("Authorization") String auth,
                              @Field("email") String email,
                              @Field("password") String password);
}
