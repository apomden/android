package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
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

import com.pomden.apomden.Adapters.SummaryAdapter;
import com.pomden.apomden.Fragments.Routers.TransferRouterFragment;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Models.Dashboard;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.AppSecrets;
import com.pomden.apomden.Utilities.Globall;
import com.pusher.client.Pusher;
import com.pusher.client.PusherOptions;
import com.pusher.client.channel.Channel;
import com.pusher.client.channel.PusherEvent;
import com.pusher.client.channel.SubscriptionEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static androidx.constraintlayout.widget.Constraints.TAG;


/**
 * A placeholder fragment containing a simple view.
 */
public class SummaryFragment extends Fragment {

    private RecyclerView recyclerView;
    private SummaryAdapter mAdapter;




    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);
        recyclerView = view.findViewById(R.id.recView);

        PusherOptions options = new PusherOptions();
        options.setCluster("eu");
        Pusher pusher = new Pusher(AppSecrets.PUSHER_API_KEY, options);

        Channel channel = pusher.subscribe(AppSecrets.PUSHER_CHANNEL_NAME);

        channel.bind(AppSecrets.PUSHER_EVENT_NAME, new SubscriptionEventListener() {
            @Override
            public void onEvent(PusherEvent event) {
                // do some work with the data
                Log.e("=======Pusherrr=======", event.getData());
            }
        });

        pusher.connect();

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
                Globall.clickFromPosition = 0;

                // navigate to fragment
                switch(position){
                    case 0:
                        // code
                        Globall.clickToPosition = 1;
                        Globall.specificClickedBy = position;
                        ((MainDashboardScreen)getActivity()).setViewPager(1);
                        break;
                    case 1:
                        // move
                        Globall.clickToPosition = 2;
                        Globall.specificClickedBy = position;
                        ((MainDashboardScreen)getActivity()).setViewPager(2);
                        break;
                    case 2:
                        //  move
                        Globall.clickToPosition = 2;
                        Globall.specificClickedBy = position;
                        ((MainDashboardScreen)getActivity()).setViewPager(2);
                        break;
                    case 3:
                        // move
                        Globall.clickToPosition = 2;
                        Globall.specificClickedBy = position;
                        ((MainDashboardScreen)getActivity()).setViewPager(3);
                        break;
                    default:
                        // move
                        Toast.makeText(getActivity(),
                                mAdapter.getUserList().get(position).getTitle(),
                                Toast.LENGTH_SHORT ).show();

                }
            }
        });

        return view;
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
                    Log.e("====Marlonnnn===", "onKey: I Have Been Pressed");

                    return true;
                }
                return false;
            }
        });
    }

}