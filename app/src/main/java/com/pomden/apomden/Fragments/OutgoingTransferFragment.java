package com.pomden.apomden.Fragments;

import android.os.Bundle;
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

import com.pomden.apomden.Adapters.TransferRecyclerAdapter;
import com.pomden.apomden.Models.Transfer;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class OutgoingTransferFragment extends Fragment {
    TextView textView;

    private RecyclerView recyclerView;
    private TransferRecyclerAdapter mAdapter;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.outgoing_transfer_fragment, container, false);
//        textView = view.findViewById(R.id.section_label);

        recyclerView = view.findViewById(R.id.outTransRecView);
        final List<Transfer> outGoing = Globall.getOutgoingTransfers(Globall.transferList);

        mAdapter =  new TransferRecyclerAdapter(
                outGoing
        );
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new TransferRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(
                        getActivity(),
                        mAdapter.getTransferList().get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}