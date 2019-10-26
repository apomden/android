package com.pomden.apomden.Fragments.Routers;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pomden.apomden.Adapters.SectionsPagerAdapter;
import com.pomden.apomden.Fragments.AllBedFragment;
import com.pomden.apomden.Fragments.BedAddNewFragment;
import com.pomden.apomden.Fragments.DepartmentAddNewFragment;
import com.pomden.apomden.Fragments.DepartmentFragment;
import com.pomden.apomden.Fragments.RoomAddNewFragment;
import com.pomden.apomden.Fragments.RoomFragment;
import com.android.apomden.R;
import com.google.android.material.tabs.TabLayout;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Utilities.Globall;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A placeholder fragment containing a simple view.
 */
public class BedRouterFragment extends Fragment {
   private ViewPager viewPager;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bed_router, container, false);


        // Set Up Tab
        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);


        return view;
    }


    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.addFragment(new DepartmentFragment(), "Departments");
        adapter.addFragment(new RoomFragment(), "Rooms");
        adapter.addFragment(new AllBedFragment(), "Beds");
        adapter.addFragment(new DepartmentAddNewFragment(), "Add Department");
        adapter.addFragment(new RoomAddNewFragment(), "Add Room");
        adapter.addFragment(new BedAddNewFragment(), "Add Bed");
        viewPager.setAdapter(adapter);


    }

    public void setViewPager(int i) {
        viewPager.setCurrentItem(i);
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
                    Log.e("Bed Activity BackP=", "onKey: I Have Been Pressed");
                    ((MainDashboardScreen)getActivity()).setViewPager(Globall.clickFromPosition);
                    return true;
                }
                return false;
            }
        });
    }



}