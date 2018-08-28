package com.dal.hrm_management.api;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Url;

/**
 * Created by Luu Ngoc Lan on 28-Aug-18.
 */

public interface RetrofitImageAPI {
    @GET()
    Call<ResponseBody> getImageDetails(@Header("Authorization") String token, @Url String url);
}