package com.android.apomden;

import android.os.Bundle;

import com.android.apomden.Fragments.BedRouterFragment;
import com.android.apomden.Fragments.ProfileRouterFragment;
import com.android.apomden.Fragments.SummaryFragment;
import com.android.apomden.Fragments.OutgoingTransferFragment;
import com.android.apomden.Fragments.IncomingTransferFragment;
import com.android.apomden.Adapters.SectionsPagerAdapter;
import com.android.apomden.Fragments.TransferRouterFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import android.view.MenuItem;

public class MainDashboardScreen extends AppCompatActivity {
    private ViewPager viewPager;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.nav_summary:
                    viewPager.setCurrentItem(0);
                    return true;
                case R.id.nav_beds:
                    viewPager.setCurrentItem(1);
                    return true;
                case R.id.nav_transfer:
                    viewPager.setCurrentItem(2);
                    return true;
                case R.id.nav_transfers:
                    viewPager.setCurrentItem(3);
                    return true;
                case R.id.nav_profile:
                    viewPager.setCurrentItem(4);
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_dashboard_screen);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        viewPager = findViewById(R.id.view_pager);
        sectionsPagerAdapter.addFragment( new SummaryFragment(), "Summary");
        sectionsPagerAdapter.addFragment( new BedRouterFragment(), "Beds");
        sectionsPagerAdapter.addFragment( new TransferRouterFragment(), "Transfer");
        sectionsPagerAdapter.addFragment( new IncomingTransferFragment(), "Transfers");
        sectionsPagerAdapter.addFragment( new ProfileRouterFragment(), "Profile");
        viewPager.setAdapter(sectionsPagerAdapter);

    }

}
