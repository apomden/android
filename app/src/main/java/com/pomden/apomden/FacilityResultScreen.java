package com.pomden.apomden;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.pomden.apomden.Adapters.FacilityAdapter;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

public class FacilityResultScreen extends AppCompatActivity {

    ListView listView;
    SharedPreferences pref;
    SharedPreferences.Editor editor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_facility_result_screen);


        listView = findViewById(R.id.facilitiesList);
        pref = getApplicationContext().getSharedPreferences("MyPref", 0);
        editor = pref.edit();


        FacilityAdapter facilityAdapter = new FacilityAdapter(this, 0, Globall.globallFacilities);
        listView.setAdapter(facilityAdapter);


        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                // set current selected item and load next page
                Globall.selectedFacility = Globall.globallFacilities.get(i);

                // populate shared preferences
                editor.putString("facEmail", Globall.selectedFacility.getEmail());
                editor.putString("facilityId", Globall.selectedFacility.getFacilityId());
                editor.putString("facilityDomain", Globall.selectedFacility.getDomain());
                editor.putString("facilityName", Globall.selectedFacility.getFacilityName());
                editor.putString("facilityCountry", Globall.selectedFacility.getFacilityCountry());
                editor.putString("facilityCity", Globall.selectedFacility.getFacilityCity());
                editor.putString("facilityRegion", Globall.selectedFacility.getFacilityRegion());
                editor.putString("facilityStreet", Globall.selectedFacility.getFacilityStreet());
                editor.putString("facilityDistrict", Globall.selectedFacility.getFacilityDistrict());
                editor.commit();

//                Log.e("String", pref.getString("facEmail", "facEmail"));

                startActivity(new Intent(getApplicationContext(), FacilityLoginScreen.class));

            }
        });




    }
}
