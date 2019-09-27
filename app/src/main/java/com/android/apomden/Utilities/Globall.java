package com.android.apomden.Utilities;

import android.util.Log;

import com.android.apomden.Models.Facility;
import com.android.apomden.Services.APISERVICE;
import com.android.apomden.Services.FINDERSERVICE;
import com.android.apomden.Services.Responser;
import com.android.apomden.Services.SearchResponsor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globall {

    public static List<Facility> globallFacilities = null;
    public static Facility selectedFacility = null;
    public static String currentFacilityUrl = null;


    public static void logUserIn(Facility facility, String url, final Responser responser) {
        Map<String, Object> postUser = UserBuilder.buildUserJson(facility);

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
                    Log.e("=====results====", rep);

                    JSONObject jsonObject = new JSONObject(rep);
                    String status = jsonObject.getString("success");
                    String error = jsonObject.getString("error");




                    if (status.equals("true")){
                        responser.onSuccess("Login Successful");

                    } else {
                        responser.onFailed("Login Failed: Wrong Password");

                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }


            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                responser.onFailed("Login Failed: Wrong Password");
            }
        });
    }

    public static void findFacility (final String email, final SearchResponsor responser) {

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apomden.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FINDERSERVICE service = retrofit.create(FINDERSERVICE.class);
        Call<ResponseBody> result = service.getCalls("user/search-by-email/" + email);

        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {

                    String rep = String.valueOf( response.body().source().readUtf8() );
                    JSONObject jsonObject = new JSONObject(rep);
                    String data = jsonObject.getString("data");
                    JSONObject dataObj  =  new JSONObject(data);
                    String staffAt = dataObj.getString("staffAt");
                    JSONArray staffArray =  new JSONArray(staffAt);

//                    Log.e("Facilities=======", rep);

                    if (staffArray.length() > 0){
                        //List of facilities

                        List<Facility> facilities = new ArrayList<>();

                        // enter loop

                        for (int i = 0; i < staffArray.length(); i++) {
                            // single creation of a Facility

                            JSONObject fullDetails = staffArray.getJSONObject(i);
                            String facility = fullDetails.getString("facility");
                            JSONObject facilityObject = new JSONObject(facility);

                            // get needables
                            String facilityId                = facilityObject.getString("_id");
                            String domain                    = facilityObject.getString("domain");
                            String facilityName              = facilityObject.getString("name");
                            String verified                  = facilityObject.getString("isVerified");
                            JSONObject facilityAddressObject = new JSONObject(facilityObject.getString("address"));


                            String facilityCountry  = facilityAddressObject.getString("country");
                            String facilityCity     = facilityAddressObject.getString("city");
                            String facilityDistrict = facilityAddressObject.getString("district");
                            String facilityStreet   = facilityAddressObject.getString("street");
                            String facilityRegion   = facilityAddressObject.getString("region");


                            // create new user with the 3 param constructor
                            Facility user =  new Facility(
                                    email,
                                    facilityId,
                                    domain
                            );

                            user.setFacilityCity(facilityCity);
                            user.setFacilityDistrict(facilityDistrict);
                            user.setFacilityRegion(facilityRegion);
                            user.setFacilityStreet(facilityStreet);
                            user.setFacilityCountry(facilityCountry);
                            user.setVerified(Boolean.getBoolean(verified));
                            user.setFacilityName(facilityName);

                            facilities.add(user);
                        }




                        responser.onSuccess(facilities);

                    } else {
                        responser.onFailed("This Email Is Not Registered Under Any Facility Please Check And Try Again");

                    }

                } catch (IOException e) {
                    e.printStackTrace();
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });
    }


    public static void getFacilityDetails (String facilityId){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apomden.com/v2/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        FINDERSERVICE service = retrofit.create(FINDERSERVICE.class);
        Call<ResponseBody> result = service.getCalls("facility/" + facilityId);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String rep = String.valueOf( response.body().source().readUtf8() );
                    Log.e("===FacilityGet======", rep);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {

            }
        });

    }
}
