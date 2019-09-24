package com.android.apomden.Services;


import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Url;

public interface FINDERSERVICE {
    @GET
    Call<ResponseBody> getCalls (@Url String url);
}
