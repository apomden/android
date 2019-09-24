package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.apomden.Models.Facility;
import com.android.apomden.Services.SearchResponsor;
import com.android.apomden.Utilities.Globall;

public class FindYourFacilityScreen extends AppCompatActivity {
    Button btnFind;
    ProgressDialog pdialog;
    EditText email;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_facility_screen);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        if(pref.contains("domain")){
            // go to Login Screen
            startActivity(new Intent(this.getApplicationContext(), FacilityLoginScreen.class));
        }

        btnFind = findViewById(R.id.btnFindFacility);
        email = findViewById(R.id.facilityEmail);
        pdialog=new ProgressDialog(this);

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (email.getText().toString().trim().length() > 0){

                    //start dialog
                    pdialog.setTitle("Loading.....");
                    pdialog.setIndeterminate(true);
                    pdialog.show();

                    Globall.findFacility(email.getText().toString().trim(), new SearchResponsor() {
                        @Override
                        public void onSuccess(Facility facility) {
                            // save in shared prefs
                            editor.putString("domain", facility.getDomain());
                            editor.putString("email", facility.getEmail());
                            editor.putString("facilityId", facility.getFacility());
                            editor.apply();

                            pdialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Success",
                                    Toast.LENGTH_LONG).show();


                            startActivity(new Intent(getApplicationContext(), FacilityLoginScreen.class));

                        }

                        @Override
                        public void onFailed(String string) {

                            pdialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    string,
                                    Toast.LENGTH_LONG).show();
                        }
                    });




                } else {

                    Toast.makeText(
                            getApplicationContext(),
                            "Error: Please enter an email in the box provided",
                            Toast.LENGTH_LONG).show();

                }


            }
        });
    }
}
