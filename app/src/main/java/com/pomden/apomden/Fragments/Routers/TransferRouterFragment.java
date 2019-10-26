package com.pomden.apomden.Fragments.Routers;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.pomden.apomden.Adapters.BedRecyclerAdapter;
import com.pomden.apomden.Adapters.SectionsPagerAdapter;
import com.pomden.apomden.Fragments.IncomingTransferFragment;
import com.pomden.apomden.Fragments.OutgoingTransferFragment;
import com.android.apomden.R;
import com.google.android.material.tabs.TabLayout;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Utilities.Globall;


/**
 * A placeholder fragment containing a simple view.
 */
public class TransferRouterFragment extends Fragment {
    private ViewPager viewPager;
    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.transfer_router, container, false);


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
        adapter.addFragment(new IncomingTransferFragment(), "Incoming");
        adapter.addFragment(new OutgoingTransferFragment(), "Outgoing");
        viewPager.setAdapter(adapter);


    }

    public void setViewPager(int number){
        viewPager.setCurrentItem(number);
    }

    @Override
    public void onResume() {
        super.onResume();

        if (Globall.clickFromPosition == 0 && Globall.specificClickedBy == 2){
            setViewPager(1);
        }

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