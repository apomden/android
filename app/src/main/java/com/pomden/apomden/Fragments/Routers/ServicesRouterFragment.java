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
import com.pomden.apomden.Fragments.ServicesAddNewFragment;
import com.pomden.apomden.Fragments.ServicesFragment;
import com.android.apomden.R;
import com.google.android.material.tabs.TabLayout;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Utilities.Globall;


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