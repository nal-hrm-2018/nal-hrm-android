package com.dal.hrm_management.models.login;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.JsonObject;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Credentials;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetLoginToken extends AsyncTask<String,Void,String> {
    OkHttpClient okHttpClient = new OkHttpClient.Builder()
            .build();
    String emai,password;

    public GetLoginToken(String emai, String password) {
        this.emai = emai;
        this.password = password;
    }
    @Override
    protected String doInBackground(String... strings) {
        RequestBody requestBody = new MultipartBody.Builder()
                .addFormDataPart("email",emai)
                .addFormDataPart("password",password)
                .setType(MultipartBody.FORM)

                .build();
        Request request = new Request.Builder()
                .addHeader("Authorization", Credentials.basic("hrm_testing","hrm_testing"))
                .url(strings[0])

                .post(requestBody)
                .build();
        try {
            Response response = okHttpClient.newCall(request).execute();
            String json = response.body().string();
            try {
                JSONObject jsonObject = new JSONObject(json);
                String token = jsonObject.getString("data");
                return token;
            } catch (JSONException e) {

                e.printStackTrace();
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
