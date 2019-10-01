package com.android.apomden.Fragments.Routers;

import android.os.Bundle;
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
import com.android.apomden.Fragments.IncomingTransferFragment;
import com.android.apomden.Fragments.OutgoingTransferFragment;
import com.android.apomden.R;
import com.google.android.material.tabs.TabLayout;


/**
 * A placeholder fragment containing a simple view.
 */
public class TransferRouterFragment extends Fragment {
    TextView textView;

    private RecyclerView recyclerView;
    private BedRecyclerAdapter mAdapter;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transfer_router, container, false);


        // Set Up Tab
        ViewPager viewPager = view.findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        // Set Tabs inside Toolbar
        TabLayout tabs = view.findViewById(R.id.result_tabs);
        tabs.setupWithViewPager(viewPager);


        return view;
    }


    private void setupViewPager(ViewPager viewPager) {

        SectionsPagerAdapter adapter = new SectionsPagerAdapter(getActivity(), getChildFragmentManager());
        adapter.addFragment(new IncomingTransferFragment(), "Incoming");
        adapter.addFragment(new OutgoingTransferFragment(), "Outgoing");
        viewPager.setAdapter(adapter);


    }
}