package com.android.apomden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Adapters.SummaryAdapter;
import com.android.apomden.Models.Dashboard;
import com.android.apomden.R;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class SummaryFragment extends Fragment {
    TextView textView;

    private List<Dashboard> dashboards = new ArrayList<>();
    private RecyclerView recyclerView;
    private SummaryAdapter mAdapter;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);
        textView = view.findViewById(R.id.section_label);
        recyclerView = view.findViewById(R.id.recView);

        Dashboard dashboard =  new Dashboard(
                "Beds Available",
                "16",
                getResources().getDrawable(R.drawable.pat),
                getResources().getDrawable(R.drawable.green_mix)
        );
        Dashboard dashboard1 =  new Dashboard(
                "Outgoing Transfers",
                "5",
                getResources().getDrawable(R.drawable.pat),
                getResources().getDrawable(R.drawable.black_mix)
        );
        Dashboard dashboard2 =  new Dashboard(
                "Incoming Transfers & Emergencies",
                "3",
                getResources().getDrawable(R.drawable.pat),
                getResources().getDrawable(R.drawable.red_mix)
        );
        Dashboard dashboard3 =  new Dashboard(
                "Turn Over Rate",
                "4 days",
                getResources().getDrawable(R.drawable.pat),
                getResources().getDrawable(R.drawable.orange_mix)
        );


        dashboards.add(dashboard);
        dashboards.add(dashboard1);
        dashboards.add(dashboard2);
        dashboards.add(dashboard3);

        mAdapter =  new SummaryAdapter(dashboards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        return view;
    }
}