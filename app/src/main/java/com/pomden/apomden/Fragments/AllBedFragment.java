package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pomden.apomden.Adapters.BedRecyclerAdapter;
import com.pomden.apomden.Fragments.Routers.BedRouterFragment;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Models.Bed;
import com.android.apomden.R;
import com.pomden.apomden.Models.Room;
import com.pomden.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class AllBedFragment extends Fragment {
    public static AllBedFragment self;
    EditText searchBed;
    private RecyclerView recyclerView;
    private BedRecyclerAdapter mAdapter;
    List<Bed> bedList;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bed_fragment, container, false);
        self = this;
        // Set Up Recycler View


        recyclerView = view.findViewById(R.id.bedRecView);
        searchBed = view.findViewById(R.id.searchBed);

        bedList = Globall.bedList;
//        Log.e("====Beds=====", String.valueOf(bedList.size()));

        mAdapter =  new BedRecyclerAdapter(bedList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new BedRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(
                        getActivity(),
                        mAdapter.getBedList().get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });



        searchBed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString().trim());
            }
        });



        return view;
    }

    private void filter (String textString){
        List<Bed> filteredList = new ArrayList<>();

        for(Bed bedItem: bedList) {
            boolean nameMatches = bedItem.getName().toLowerCase().contains(textString.toLowerCase());
            boolean genderMatches = bedItem.getSex().toLowerCase().contains(textString.toLowerCase());
            boolean departmentMatches =  bedItem.getRoomName().toLowerCase().contains(textString.toLowerCase());
            boolean statusMatches = bedItem.getStatus().toLowerCase().contains(textString.toLowerCase());

            if (nameMatches || genderMatches || departmentMatches || statusMatches){
                filteredList.add(bedItem);
            }
        }

        mAdapter.filterList(filteredList);

    }



    @Override
    public void onResume() {
        super.onResume();

        if(getView() == null){
            return;
        }

        getView().setFocusableInTouchMode(true);
        getView().requestFocus();
        getView().setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {

                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    // handle back button's click listener
                    if (Globall.clickFromPosition == Globall.clickToPosition){
                        BedRouterFragment.self.setViewPager(Globall.sameSituationPosition);

                        Globall.clickFromPosition =1;
                        Globall.clickToPosition=1;
                        Globall.specificClickedBy=0;
                        Globall.sameSituationPosition=0;

                    } else {
                        MainDashboardScreen.self.setViewPager(Globall.clickFromPosition);

                        Globall.clickFromPosition =1;
                        Globall.clickToPosition=1;
                        Globall.specificClickedBy=0;
                        Globall.sameSituationPosition=0;
                    }
                    return true;
                }
                return false;
            }
        });
    }


    public void setMlist(List<Bed> bed){
        mAdapter.setBedList(bed);
    }



}