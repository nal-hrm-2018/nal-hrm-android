package com.dal.hrm_management.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiImageClient {
    public static final String BASE_URL_IMAGE = "http://52.14.120.158/avatar/";


    private static Retrofit retrofit = null;

    public static Retrofit getImageClient() {
        if (retrofit == null) {
            retrofit = new Retrofit.Builder().baseUrl(BASE_URL_IMAGE).addConverterFactory(GsonConverterFactory.create()).build();
        }
        return retrofit;
    }

}
