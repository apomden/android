package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.apomden.Models.User;
import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

public class FacilityLoginScreen extends AppCompatActivity {
    Button enterMainScreen;
    EditText emailText, passwordText, facilityText, domainText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);

        // check shared preferences
        if(pref.contains("domain")){

            String email = pref.getString("email", null);
            String domain = pref.getString("domain", null);
            String facilityId = pref.getString("facilityId", null);

            Log.e("======Details======", email + domain + facilityId );

            User user = new User(
                    email,
                    facilityId,
                    "1234",
                    domain
            );

            Globall.logUserIn(user, "user/login/", new Responser() {
                @Override
                public void onSuccess(String string) {
                    Log.e("===Passed===", string);
                }

                @Override
                public void onFailed(String string) {
                    Log.e("===Failed===", string);
                }
            });

            enterMainScreen = (Button) findViewById(R.id.btnEnterMainPage);
            emailText = (EditText) findViewById(R.id.email);
            passwordText = (EditText) findViewById(R.id.password);


            enterMainScreen.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

//                if ( isNotEmpty(emailText) && isNotEmpty(passwordText) ){
//                    Log.e("==== Is Not Empty===", emailText.getText().toString().trim());
//                } else {
//                    Log.e("==== Is Empty===", emailText.getText().toString().trim());
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
