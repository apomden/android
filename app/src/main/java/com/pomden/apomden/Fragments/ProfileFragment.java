package com.pomden.apomden.Fragments;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pomden.apomden.FindYourFacilityScreen;
import com.pomden.apomden.MainDashboardScreen;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

import java.util.ArrayList;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileFragment extends Fragment {

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.facility_profile, container, false);

        SharedPreferences pref = getActivity().getSharedPreferences("MyPref", 0);
        final SharedPreferences.Editor editor = pref.edit();


        LinearLayout llProfileBeds = view.findViewById(R.id.profileBeds);
        LinearLayout llProfileDept = view.findViewById(R.id.llProfileDept);
        LinearLayout llProfileServices = view.findViewById(R.id.llProfileServices);
        LinearLayout llProfileTransfers = view.findViewById(R.id.llProfileTransfers);
        LinearLayout llProfileLogout = view.findViewById(R.id.llProfileLogout);


        TextView profileFacilityName = view.findViewById(R.id.profileFacilityName);
        TextView profileFacilityCity = view.findViewById(R.id.profileFacilityCity);
        TextView primaryEmail = view.findViewById(R.id.primaryEmail);
        TextView primaryPhoneNumber = view.findViewById(R.id.primaryPhoneNumber);
        TextView profileTranfers = view.findViewById(R.id.profileTranfers);
        TextView secondaryEmail  = view.findViewById(R.id.secondaryEmail);
        TextView secondaryPhoneNumber  = view.findViewById(R.id.secondaryPhoneNumber);
        TextView profileBedsNumber  = view.findViewById(R.id.profileBedsNumber);
        TextView profileServices  =  view.findViewById(R.id.profileServices);
        TextView profileDepartments = view.findViewById(R.id.profileDepartments);



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



        llProfileBeds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ((MainDashboardScreen)getActivity()).setViewPager(1);
                Toast.makeText(view.getContext(), "profileBeds", Toast.LENGTH_SHORT ).show();
            }
        });


        llProfileDept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainDashboardScreen)getActivity()).setViewPager(1);
                Toast.makeText(view.getContext(), "Department", Toast.LENGTH_SHORT ).show();
            }
        });



        llProfileTransfers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainDashboardScreen)getActivity()).setViewPager(2);
                Toast.makeText(view.getContext(), "Transfers", Toast.LENGTH_SHORT ).show();
            }
        });



        llProfileServices.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((MainDashboardScreen)getActivity()).setViewPager(3);
                Toast.makeText(view.getContext(), "Services", Toast.LENGTH_SHORT ).show();
            }
        });



        llProfileLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editor.clear();
                editor.commit();

                //Empty Global items
                Globall.globallFacilities = new ArrayList<>();
                Globall.selectedFacility = null;
                Globall.currentFacilityUrl = null;
                Globall.dashboards = new ArrayList<>();
                Globall.tagList = new ArrayList<>();
                Globall.serviceList = new ArrayList<>();
                Globall.roomList = new ArrayList<>();
                Globall.departmentList = new ArrayList<>();
                Globall.bedList = new ArrayList<>();
                Globall.contactGloball = null;
                Globall.transferList =  new ArrayList<>();

                startActivity(new Intent(getActivity(), FindYourFacilityScreen.class));
                Toast.makeText(view.getContext(), "Log Out Successful", Toast.LENGTH_SHORT ).show();
            }
        });







        return view;
    }
}