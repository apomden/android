package com.android.apomden.Utilities;

import android.util.Log;

import com.android.apomden.Models.User;
import com.android.apomden.Services.APISERVICE;
import com.android.apomden.Services.Responser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globall {

    public static void logUserIn(User user, String url, final Responser responser) {
        Map<String, Object> postUser = UserBuilder.buildUserJson(user);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apomden.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE service = retrofit.create(APISERVICE.class);
        Call<ResponseBody> result = service.sendUser(url, postUser);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                try {

                    String rep = String.valueOf(response.body().source().readUtf8());


                    JSONObject jsonObject = new JSONObject(rep);
                    String status = jsonObject.getString("success");
                    String error = jsonObject.getString("error");

                    if (status.equals("true")){
                        responser.onSuccess("Login Successful");

                    } else {
                        responser.onFailed(error);

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                responser.onFailed("Login Failed");
            }
        });
    }
}
