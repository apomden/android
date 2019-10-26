package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.R;
import com.pomden.apomden.Adapters.FacilityAdapter2;
import com.pomden.apomden.Adapters.TransferRecyclerAdapter;
import com.pomden.apomden.Models.Transfer;
import com.pomden.apomden.Utilities.Globall;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class FindFacilityFragment extends Fragment {


    ListView listView;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_facility_result_screen, container, false);

        listView = view.findViewById(R.id.facilitiesList);

        FacilityAdapter2 facilityAdapter = new FacilityAdapter2(getActivity(), 0, Globall.globallGotten);
        listView.setAdapter(facilityAdapter);


        return view;
    }
}