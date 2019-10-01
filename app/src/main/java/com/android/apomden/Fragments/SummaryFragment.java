package com.android.apomden.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Adapters.SummaryAdapter;
import com.android.apomden.Models.Dashboard;
import com.android.apomden.R;
import com.android.apomden.Services.Responser;
import com.android.apomden.Utilities.Globall;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.Arrays;
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
//        textView = view.findViewById(R.id.section_label);
        recyclerView = view.findViewById(R.id.recView);

        Log.e("===Logger Dept===", String.valueOf(Globall.departmentList.size()));
        Log.e("===Logger Transfer===", String.valueOf(Globall.transferList.size()));


        Log.e("===Logger InComing===", String.valueOf(Globall.getIncomingTransfers(Globall.transferList).size()));
        Log.e("===Logger OutGoing===", String.valueOf(Globall.getOutgoingTransfers(Globall.transferList).size()));


        Dashboard dashboard =  new Dashboard(
                "Beds Available",
                String.valueOf(Globall.bedList.size()),
                getResources().getDrawable(R.drawable.pat),
                getResources().getDrawable(R.drawable.green_mix)
        );
        Dashboard dashboard1 =  new Dashboard(
                "Outgoing Transfers",
                String.valueOf((Globall.getOutgoingTransfers(Globall.transferList).size())),
                getResources().getDrawable(R.drawable.tranfip),
                getResources().getDrawable(R.drawable.black_mix)
        );
        Dashboard dashboard2 =  new Dashboard(
                "Incoming Transfers & Emergencies",
                String.valueOf(Globall.getIncomingTransfers(Globall.transferList).size()),
                getResources().getDrawable(R.drawable.transabat),
                getResources().getDrawable(R.drawable.red_mix)
        );
        Dashboard dashboard3 =  new Dashboard(
                "Turn Over Rate",
                "4 days",
                getResources().getDrawable(R.drawable.anaomati),
                getResources().getDrawable(R.drawable.orange_mix)
        );


        Globall.dashboards = Arrays.asList(
                dashboard, dashboard1, dashboard2, dashboard3
        );

        mAdapter =  new SummaryAdapter(Globall.dashboards);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new SummaryAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(
                        getActivity(),
                        Globall.dashboards.get(position).getTitle(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}