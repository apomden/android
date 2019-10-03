package com.android.apomden.Fragments.Routers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.android.apomden.Adapters.BedRecyclerAdapter;
import com.android.apomden.Adapters.SectionsPagerAdapter;
import com.android.apomden.Fragments.AllBedFragment;
import com.android.apomden.Fragments.DepartmentFragment;
import com.android.apomden.Fragments.ServicesAddNewFragment;
import com.android.apomden.Fragments.ServicesFragment;
import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;
import com.google.android.material.tabs.TabLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class ServicesRouterFragment extends Fragment {
    private ViewPager viewPager;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_router, container, false);


        // Set Up Tab
        viewPager = view.findViewById(R.id.viewpager);
        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.addFragment(new ServicesFragment(), "Services");
        adapter.addFragment(new ServicesAddNewFragment(), "Add A Service");
        viewPager.setAdapter(adapter);

        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
//                Log.e("+++PageScrolled++++", String.valueOf(position));
            }

            @Override
            public void onPageSelected(int position) {
                Log.e("+++PageSelected++++", String.valueOf(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
//                Log.e("PageChangeScllSChanged", String.valueOf(state));
            }
        });


        return view;
    }



    public void setViewPager (int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }




}