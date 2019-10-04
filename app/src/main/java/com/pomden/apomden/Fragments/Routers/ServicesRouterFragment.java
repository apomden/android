package com.pomden.apomden.Fragments.Routers;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.pomden.apomden.Adapters.SectionsPagerAdapter;
import com.pomden.apomden.Fragments.ServicesAddNewFragment;
import com.pomden.apomden.Fragments.ServicesFragment;
import com.android.apomden.R;
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


        return view;
    }



    public void setViewPager (int fragmentNumber) {
        viewPager.setCurrentItem(fragmentNumber);
    }




}