package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import com.android.apomden.Utilities.Globall;

public class FacilityResultScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_result_screen);

        Log.e("========sides====", String.valueOf(Globall.globallFacilities.size()));

    }
}
