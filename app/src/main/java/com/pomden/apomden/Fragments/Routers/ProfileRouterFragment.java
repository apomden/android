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
import com.pomden.apomden.Fragments.OutgoingTransferFragment;
import com.pomden.apomden.Fragments.ProfileFragment;
import com.android.apomden.R;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Utilities.Globall;
import com.google.android.material.tabs.TabLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileRouterFragment extends Fragment {
//    TextView textView;

    ViewPager viewPager;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_router, container, false);
//        textView = view.findViewById(R.id.section_label);

        Log.e("====Contact=====", Globall.contactGloball.getPrimaryEmail());


        viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);

        return view;
    }

    // Add Fragments to Tabs
    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.addFragment(new ProfileFragment(), "My Profile");
        adapter.addFragment(new OutgoingTransferFragment(), "Change Password");
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