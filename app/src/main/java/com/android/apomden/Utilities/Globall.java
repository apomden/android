package com.android.apomden.Utilities;

import android.util.Log;

import com.android.apomden.Models.Bed;
import com.android.apomden.Models.Contact;
import com.android.apomden.Models.Dashboard;
import com.android.apomden.Models.Department;
import com.android.apomden.Models.Facility;
import com.android.apomden.Models.Room;
import com.android.apomden.Models.Service;
import com.android.apomden.Models.Tag;
import com.android.apomden.Services.APISERVICE;
import com.android.apomden.Services.FINDERSERVICE;
import com.android.apomden.Services.INCLUDE;
import com.android.apomden.Services.Responser;
import com.android.apomden.Services.SearchResponsor;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.net.CookieHandler;
import java.net.CookieManager;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import okhttp3.JavaNetCookieJar;
import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Globall {

    public static List<Facility> globallFacilities = new ArrayList<>();
    public static Facility selectedFacility = null;
    public static String currentFacilityUrl = null;
    public static List<Dashboard> dashboards = new ArrayList<>();

    public static List<Tag> tagList = new ArrayList<>();
    public static List<Service> serviceList = new ArrayList<>();
    public static List<Room> roomList = new ArrayList<>();
    public static List<Department> departmentList = new ArrayList<>();
    public static List<Bed> bedList = new ArrayList<>();
    public static Contact contact = null;










    public static void logUserIn(Facility facility, String url, final Responser responser) {
        Map<String, Object> postUser = UserBuilder.buildUserJson(facility);

        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // init cookie manager
        CookieHandler cookieHandler = new CookieManager();

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apomden.com/v2/")
                .client(client)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APISERVICE service = retrofit.create(APISERVICE.class);
        Call<ResponseBody> result = service.sendUser(url, postUser);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {



                try {

                    String rep = String.valueOf(response.body().source().readUtf8());
//                    Log.e("=====results====", rep);

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
        HttpLoggingInterceptor interceptor = new HttpLoggingInterceptor();
        interceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        // init cookie manager
        CookieHandler cookieHandler = new CookieManager();

        OkHttpClient client = new OkHttpClient.Builder().addNetworkInterceptor(interceptor)
                .cookieJar(new JavaNetCookieJar(cookieHandler))
                .connectTimeout(10, TimeUnit.SECONDS)
                .writeTimeout(10, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.apomden.com/v2/facility/")
                .addConverterFactory(GsonConverterFactory.create())
                .client(client)
                .build();
        INCLUDE service = retrofit.create(INCLUDE.class);
        Call<ResponseBody> result = service.sendUser(facilityId);
        result.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    String rep = String.valueOf( response.body().source().readUtf8() );
                    formatJson(rep);

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

    public static void formatJson ( String gottenString ) throws JSONException {
        JSONObject jsonObject = new JSONObject(gottenString);
        JSONObject dataObject = new JSONObject(jsonObject.getString("data"));
        JSONArray staffArrayObject = new JSONArray(dataObject.getString("staff"));
        JSONArray patientArrayObject =  new JSONArray(dataObject.getString("patients"));
        JSONObject addressObject = new JSONObject(dataObject.getString("address"));
        JSONObject contactObject = new JSONObject(dataObject.getString("contact"));
        JSONArray tagArrayObject =  new JSONArray(dataObject.getString("tags"));
        JSONArray departmentArrayObject =  new JSONArray(dataObject.getString("departments"));
        JSONArray servicesArrayObject = new JSONArray(dataObject.getString("services"));
        JSONArray announcementArrayObject =  new JSONArray(dataObject.getString("announcements"));


        // Deal With Tags
        for (int i = 0; i < tagArrayObject.length(); i++){
            JSONObject tagEachObject = new JSONObject(tagArrayObject.getString(i));
            // create Tags With Results

            Tag tag = new Tag(
                    tagEachObject.getString("_id"),
                    tagEachObject.getString("value")
            );

            tagList.add(tag);
        }


        //Deal With Services
        for (int i = 0; i < servicesArrayObject.length(); i++){
            JSONObject servicesEachObject = new JSONObject(servicesArrayObject.getString(i));

            Service service =  new Service(
                    servicesEachObject.getString("name"),
                    servicesEachObject.getString("description"),
                    servicesEachObject.getString("_id")
            );

            serviceList.add(service);

        }


        //Deal With Departments
        for (int i = 0; i < departmentArrayObject.length(); i++){
            JSONObject departmentEachObject = new JSONObject(departmentArrayObject.getString(i));
            String deptName  =  departmentEachObject.getString("name");
            JSONArray deptRoomsArray = new JSONArray(departmentEachObject.getString("rooms"));

            // Deal With Dep rooms
            for (int j = 0; j < deptRoomsArray.length(); j++) {

                String roomSex = new JSONObject( deptRoomsArray.getString(j) ).getString("sex");
                String roomId = new JSONObject( deptRoomsArray.getString(j) ).getString("_id");
                JSONArray roomBedsArray = new JSONArray ( new JSONObject (deptRoomsArray.getString(j) ).getString("beds") );


                // Deal With the Beads
                for (int k = 0; k < roomBedsArray.length() ; k++) {
                    JSONObject roomBedEachObject = new JSONObject(roomBedsArray.getString(i));
                    /*
                    {@name,tags, isOccupied, status, lastUsedBy}
                    * */
                    Bed bed = new Bed();
                    bed.setId(roomBedEachObject.getString("_id"));
                    bed.setName(roomBedEachObject.getString("name"));
                    bed.setOccupied( Boolean.valueOf(roomBedEachObject.getString("isOccupied")) );
                    bed.setStatus(roomBedEachObject.getString("status"));
                    bedList.add(bed);

                }
                //

                Room room = new Room(
                        roomSex,
                        roomId,
                        bedList
                );

                roomList.add(room);


            }

            Department department =  new Department(
                    departmentEachObject.getString("_id"),
                    deptName,
                    roomList
            );

            departmentList.add(department);
        }


        Log.e("=====Department==", String.valueOf(departmentList.size()));

        Log.e("=====Tag==", String.valueOf(tagList.size()));

        Log.e("=====Service List==", String.valueOf(serviceList.size()));

        Log.e("=====Room Count==", String.valueOf(roomList.size()));

        Log.e("=====Bed Count==", String.valueOf(bedList.size()));


    }// end of formatJSON




}
