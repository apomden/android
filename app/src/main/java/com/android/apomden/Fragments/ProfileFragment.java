package com.android.apomden.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Adapters.BedRecyclerAdapter;
import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileFragment extends Fragment {

     TextView profileFacilityName, profileFacilityCity;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facility_profile, container, false);
        profileFacilityName = view.findViewById(R.id.profileFacilityName);
        profileFacilityCity = view.findViewById(R.id.profileFacilityCity);

//        Log.e("====hshhss===" , Globall.selectedFacility.getFacilityCity());

        profileFacilityName.setText(Globall.selectedFacility.getDomain());
        profileFacilityCity.setText(Globall.selectedFacility.getFacilityCity());




        return view;
    }
}