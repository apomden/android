package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.android.apomden.Models.Facility;
import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

public class FacilityLoginScreen extends AppCompatActivity {
    Button enterMainScreen;
    EditText passwordText;
    TextView textTop, textBeforeBox;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        SharedPreferences.Editor editor = pref.edit();



        // check shared preferences
        if(pref.contains("domain")){

            String email = pref.getString("email", null);
            String domain = pref.getString("domain", null);
            String facilityId = pref.getString("facilityId", null);

            Log.e("======Details======", email + domain + facilityId );

            Facility facility = new Facility(
                    email,
                    facilityId,
                    "123",
                    domain
            );



            //TODO:: remove logic from here and put in an onclick
            Globall.logUserIn(facility, "user/login/", new Responser() {
                @Override
                public void onSuccess(String string) {
                    Log.e("===Passed===", string);
                }

                @Override
                public void onFailed(String string) {
                    Log.e("===Failed===", string);
                }
            });

            enterMainScreen = findViewById(R.id.btnLoginToFacility);
            passwordText    = findViewById(R.id.facilityPassword);
            textTop         = findViewById(R.id.textTop);
            textBeforeBox   = findViewById(R.id.textBeforeBox);

            textTop.setText("Welcome To Koobi");

//            editor.clear();
//            editor.commit();


            enterMainScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    //TODO:: Check for non emptiness of text box
                    //TODO:: Run logic of login
                    //TODO:: perform Action after results return

//                if ( isNotEmpty(passwordText) ){
//                    Log.e("==== Is Not Empty===", emailText.getText().toString().trim());
//                }

                    startActivity(new Intent(view.getContext(), FacilityDashboardScreen.class));

                }
            });




        } else {

            startActivity(new Intent(this.getApplicationContext(), FindYourFacilityScreen.class));
        }





    }

    public boolean isNotEmpty (EditText editText) {
        return editText.getText().toString().trim().length() > 0;
    }


}
