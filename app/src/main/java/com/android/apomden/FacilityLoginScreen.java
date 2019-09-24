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
import android.widget.TextView;
import android.widget.Toast;

import com.android.apomden.Models.Facility;
import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

public class FacilityLoginScreen extends AppCompatActivity {
    Button enterMainScreen;
    EditText passwordText;
    TextView textTop, textBeforeBox;
    ProgressDialog pdialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        final Facility facility = Globall.selectedFacility;


        enterMainScreen = findViewById(R.id.btnLoginToFacility);
        passwordText    = findViewById(R.id.facilityPassword);
        textTop         = findViewById(R.id.textTop);
        textBeforeBox   = findViewById(R.id.textBeforeBox);
        pdialog=new ProgressDialog(this);

        textTop.setText(facility.getFacilityName());
        textBeforeBox.setText(facility.getFacilityAddress());


        enterMainScreen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if ( isNotEmpty(passwordText) ){
                    //start dialog
                    pdialog.setTitle("Loading.....");
                    pdialog.setIndeterminate(true);
                    pdialog.show();

                    facility.setPassword(passwordText.getText().toString().trim());

                    //TODO:: remove logic from here and put in an onclick
                    Globall.logUserIn(facility, "user/login/", new Responser() {
                        @Override
                        public void onSuccess(String string) {
                            Log.e("===Passed===", string);

                            pdialog.dismiss();

                            Toast.makeText(
                                    getApplicationContext(),
                                    "Success",
                                    Toast.LENGTH_LONG).show();

                            Globall.currentFacilityUrl = "https://www.apomden.com/facility/" + facility.getDomain();

                            startActivity(new Intent(getApplicationContext(), FacilityDashboardScreen.class));
                        }

                        @Override
                        public void onFailed(String string) {
                            Log.e("===Failed===", string);
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
                            "Please Enter Password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });




    }



    public boolean isNotEmpty (EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }


}
