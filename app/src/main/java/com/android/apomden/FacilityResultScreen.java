package com.android.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.android.apomden.Adapters.FacilityAdapter;
import com.android.apomden.Utilities.Globall;

public class FacilityResultScreen extends AppCompatActivity {

    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_result_screen);


        listView = findViewById(R.id.facilitiesList);

        FacilityAdapter facilityAdapter = new FacilityAdapter(this, 0, Globall.globallFacilities);
        listView.setAdapter(facilityAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // set current selected item and load next page
                Globall.selectedFacility = Globall.globallFacilities.get(i);

                startActivity(new Intent(getApplicationContext(), FacilityLoginScreen.class));



            }
        });




    }
}
