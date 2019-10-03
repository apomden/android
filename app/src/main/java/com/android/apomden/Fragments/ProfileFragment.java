package com.android.apomden.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.util.TimingLogger;
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

     private TextView profileFacilityName, profileDepartments,
             profileFacilityCity, secondaryPhoneNumber,
             primaryEmail, profileTranfers, secondaryEmail,
             primaryPhoneNumber,profileBedsNumber, profileServices;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facility_profile, container, false);
        profileFacilityName = view.findViewById(R.id.profileFacilityName);
        profileFacilityCity = view.findViewById(R.id.profileFacilityCity);
        primaryEmail = view.findViewById(R.id.primaryEmail);
        primaryPhoneNumber = view.findViewById(R.id.primaryPhoneNumber);
        profileTranfers = view.findViewById(R.id.profileTranfers);
        secondaryEmail  = view.findViewById(R.id.secondaryEmail);
        secondaryPhoneNumber  = view.findViewById(R.id.secondaryPhoneNumber);
        profileBedsNumber  = view.findViewById(R.id.profileBedsNumber);
        profileServices  =  view.findViewById(R.id.profileServices);
        profileDepartments = view.findViewById(R.id.profileDepartments);



        profileFacilityName.setText(Globall.selectedFacility.getDomain());
        profileFacilityCity.setText(Globall.selectedFacility.getFacilityCity() + " , " + Globall.selectedFacility.getFacilityCountry());
        primaryEmail.setText(Globall.contactGloball.getPrimaryEmail());
        primaryPhoneNumber.setText(Globall.contactGloball.getPrimaryPhoneNumber());
        profileTranfers.setText("Transfers ( " + Globall.transferList.size() + " ) ");
        secondaryPhoneNumber.setText(Globall.contactGloball.getSecondaryPhoneNumber());
        secondaryEmail.setText(Globall.contactGloball.getSecondaryEmail());
        profileBedsNumber.setText( " Bed ( " + Globall.bedList.size() + " ) ");
        profileServices.setText("Services ( " + Globall.serviceList.size() + " ) ");
        profileDepartments.setText("Departments ( " + Globall.departmentList.size() + " ) ");




        return view;
    }
}