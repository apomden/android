package com.android.apomden.Services;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface INCLUDE {
    @Headers({
            "Content-Type: application/json",
            "Access-Control-Allow-Credentials: true",
            "Access-Control-Allow-Origin: *"
    })
    @GET
    Call<ResponseBody> sendUser (

            @Url String url

    );
}
