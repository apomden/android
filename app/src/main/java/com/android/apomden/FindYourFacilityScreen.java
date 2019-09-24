package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.android.apomden.Models.User;
import com.android.apomden.Services.Responser;
import com.android.apomden.Services.SearchResponsor;
import com.android.apomden.Utilities.Globall;

public class FindYourFacilityScreen extends AppCompatActivity {
    Button btnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_facility_screen);

        SharedPreferences pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();

        btnFind = findViewById(R.id.btnFindFacility);

        Globall.findFacility("samuel.opokuagyemang@gmail.com", new SearchResponsor() {
            @Override
            public void onSuccess(User user) {
                Log.e("====Domain======", user.getDomain());
                Log.e("====Email======", user.getEmail());
                Log.e("====FacilityId======", user.getFacility());

                // save in shared prefs
                editor.putString("domain", user.getDomain());
                editor.putString("email", user.getEmail());
                editor.putString("facilityId", user.getFacility());
                editor.apply();
            }

            @Override
            public void onFailed(String string) {
                Log.e("====Error======", string);
            }
        });

        btnFind.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(view.getContext(), FacilityResultScreen.class));

            }
        });
    }
}
