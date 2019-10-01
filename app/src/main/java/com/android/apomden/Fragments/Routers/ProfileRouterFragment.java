package com.android.apomden.Fragments.Routers;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.android.apomden.Adapters.SectionsPagerAdapter;
import com.android.apomden.Fragments.IncomingTransferFragment;
import com.android.apomden.Fragments.OutgoingTransferFragment;
import com.android.apomden.Fragments.ProfileFragment;
import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;
import com.google.android.material.tabs.TabLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class ProfileRouterFragment extends Fragment {
//    TextView textView;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile_router, container, false);
//        textView = view.findViewById(R.id.section_label);

        Log.e("====Contact=====", Globall.contactGloball.getPrimaryEmail());


        ViewPager viewPager = view.findViewById(R.id.viewpager);
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
}