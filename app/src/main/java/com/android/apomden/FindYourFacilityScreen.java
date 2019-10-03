package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.apomden.Models.Department;
import com.android.apomden.Models.Facility;
import com.android.apomden.Models.Transfer;
import com.android.apomden.Services.FacilityDetailsResponser;
import com.android.apomden.Services.Responser;
import com.android.apomden.Services.SearchResponsor;
import com.android.apomden.Services.TransferDetailsResponser;
import com.android.apomden.Utilities.Globall;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FindYourFacilityScreen extends AppCompatActivity {
    Button btnFind;
    ProgressDialog pdialog;
    EditText email;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_facility_screen);

        btnFind = findViewById(R.id.btnFindFacility);
        email = findViewById(R.id.facilityEmail);
        pdialog=new ProgressDialog(this);

        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();

//        editor.clear();



        if(pref.contains("email")){

            String emailUsedPreviously = pref.getString("email", "email");
//            Log.e("====nyenya===", pref.getString("lollipop", "lollipop"));

            if(pref.contains("lollipop")){
//                editor.clear();
                String lollipop = pref.getString("lollipop", "lollipop");
                String facName  = pref.getString("facilityName", "facilityName");
                String facId    = pref.getString("facilityId", "facilityId");
                String facilityEmail = pref.getString("facEmail", "facEmail");
                String facilityDomain = pref.getString("facilityDomain", "facilityDomain");
                String facilityCountry = pref.getString("facilityCountry", "facilityCountry");
                String facilityCity = pref.getString("facilityCity", "facilityCity");
                String facilityRegion = pref.getString("facilityRegion", "facilityRegion");
                String facilityStreet = pref.getString("facilityStreet", "facilityStreet");
                String facilityDistrict = pref.getString("facilityDistrict", "facilityDistrict");

//                Log.e("====email===", facName);
//                Log.e("====Scolaris=====", pref.getString("facEmail", "facEmail"));


                final Facility facility   = new Facility();

                facility.setPassword(lollipop);
                facility.setFacilityName(facName);
                facility.setEmail(facilityEmail);
                facility.setFacilityId(facId);
                facility.setDomain(facilityDomain);
                facility.setFacilityCountry(facilityCountry);
                facility.setFacilityCity(facilityCity);
                facility.setFacilityRegion(facilityRegion);
                facility.setFacilityStreet(facilityStreet);
                facility.setFacilityDistrict(facilityDistrict);


                //start dialog
                pdialog.setTitle("Logging You Into " + facName + " with email " + emailUsedPreviously + " Please Wait...");
                pdialog.setIndeterminate(true);
                pdialog.show();

                Globall.selectedFacility = facility;

//                Log.e("====Name===", facName);
//                Log.e("====City=====", Globall.selectedFacility.getFacilityCity());



                Globall.logUserIn(Globall.selectedFacility, "user/login/", new Responser() {
                            @Override
                            public void onSuccess(String string) {


//                                Log.e("===Passed===", string);
                                editor.putString("facName",  Globall.selectedFacility.getDomain());
                                editor.putString("lollipop", Globall.selectedFacility.getPassword());
                                editor.putString("facId",    Globall.selectedFacility.getFacilityId());
                                editor.commit();



                                Globall.currentFacilityUrl = "https://www.apomden.com/facility/" + Globall.selectedFacility.getDomain();
                                Globall.getFacilityTransfers(Globall.selectedFacility.getDomain(), new TransferDetailsResponser() {
                                    @Override
                                    public void onSuccess(List<Transfer> transfers) {

//                                        Log.e("==transfer==", String.valueOf(transfers.size()));
                                        Globall.transferList = transfers;

                                        Globall.getFacilityDetails(Globall.selectedFacility.getFacilityId(), new FacilityDetailsResponser() {
                                            @Override
                                            public void onSuccess(List<Department> departments) {
                                                Globall.departmentList = departments;


                                                Toast.makeText(
                                                        getApplicationContext(),
                                                        "Success",
                                                        Toast.LENGTH_SHORT).show();

                                                pdialog.dismiss();

                                                startActivity(new Intent(getApplicationContext(), MainDashboardScreen.class));

                                            }

                                            @Override
                                            public void onFailed(String string) {

                                            }
                                        });
                                    }

                                    @Override
                                    public void onFailed(String string) {

                                    }
                                });

                            }

                            @Override
                            public void onFailed(String string) {
                                Toast.makeText(
                                        getApplicationContext(),
                                        "Error While Logging In..",
                                        Toast.LENGTH_SHORT).show();
                            }
                        });
            }



        }




        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String emailGotten = email.getText().toString().trim();

                if (emailGotten.length() > 0){

                    searchWithEmail(emailGotten);

                } else {

                    Toast.makeText(
                            getApplicationContext(),
                            "Error: Please enter an email in the box provided",
                            Toast.LENGTH_LONG).show();

                }


            }
        });
    }

    public void searchWithEmail (final String emailGotten) {
        //start dialog
        pdialog.setTitle("Loading Facilities For " + emailGotten + ". Please Wait...");
        pdialog.setIndeterminate(true);
        pdialog.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                pdialog.dismiss();
            }
        });
        pdialog.show();

        Globall.findFacility(emailGotten, new SearchResponsor() {

            @Override
            public void onSuccess(List<Facility> facilityList) {
                // save in shared prefs
                editor.putString("email", emailGotten);
                editor.commit();

                Globall.globallFacilities = facilityList;


                pdialog.dismiss();

                Toast.makeText(
                        getApplicationContext(),
                        "Success",
                        Toast.LENGTH_SHORT).show();


                startActivity(new Intent(getApplicationContext(), FacilityResultScreen.class));

            }

            @Override
            public void onFailed(String string) {

                pdialog.dismiss();

                Toast.makeText(
                        getApplicationContext(),
                        string,
                        Toast.LENGTH_SHORT).show();
            }
        });
    }



}
