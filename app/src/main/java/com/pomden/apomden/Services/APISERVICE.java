package com.pomden.apomden.Services;


import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;
import retrofit2.http.Url;

public interface APISERVICE {
    @Headers({
            "Content-Type: application/json",
            "Access-Control-Allow-Credentials: true",
            "Access-Control-Allow-Origin: *"
    })
    @POST
    Call<ResponseBody> sendUser (
            @Url String url,
            @Body Map<String, Object> data
    );
}
