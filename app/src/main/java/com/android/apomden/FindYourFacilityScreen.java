package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

public class FindYourFacilityScreen extends AppCompatActivity {
    Button btnFind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_your_facility_screen);

        btnFind = findViewById(R.id.btnFindFacility);

        Globall.findFacility("samuel.opokuagyemang@gmail.com", new Responser() {
            @Override
            public void onSuccess(String string) {

            }

            @Override
            public void onFailed(String string) {

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
