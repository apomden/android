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

import java.util.ArrayList;

import javax.security.auth.login.LoginException;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A placeholder fragment containing a simple view.
 */
public class BedRouterFragment extends Fragment {
   private ViewPager viewPager;
   public static BedRouterFragment self;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        self = this;
        View view = inflater.inflate(R.layout.bed_router, container, false);


        // Set Up Tab
        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

        tabs.addOnTabSelectedListener(new TabLayout.BaseOnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    // Set Special Global Department Send Room Array Click Variable To Null
                    Log.e("Department", "Department Has Been Selected Fresh" );
                    RoomFragment.self.setMlist(Globall.roomList);
                }

                if (position == 1) {
                    // Set Special Global Department Send Room Array Click Variable To Null
                    Log.e("Department", "Department Has Been Selected Fresh" );
                    AllBedFragment.self.setMlist(Globall.bedList);
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 1) {
                   RoomFragment.self.setMlist(Globall.roomList);
                }

                if (position == 2) {
                    AllBedFragment.self.setMlist(Globall.bedList);
                }
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                if (position == 0) {
                    RoomFragment.self.setMlist(Globall.roomList);
                    Log.e("Department", "Department Has Been Reselected" );
                }
                if (position == 2) {
                    AllBedFragment.self.setMlist(Globall.bedList);
                }
            }
        });

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



}