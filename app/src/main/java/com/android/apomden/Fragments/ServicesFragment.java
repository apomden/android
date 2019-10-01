package com.android.apomden.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Adapters.BedRecyclerAdapter;
import com.android.apomden.Adapters.DepartmentRecyclerAdapter;
import com.android.apomden.Adapters.ServiceRecyclerAdapter;
import com.android.apomden.Models.Department;
import com.android.apomden.Models.Service;
import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class ServicesFragment extends Fragment {
    EditText searchBed;

    private RecyclerView recyclerView;
    private ServiceRecyclerAdapter mAdapter;
    private List<Service> mList;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_fragment, container, false);

        mList = Globall.serviceList;
        searchBed = view.findViewById(R.id.searchBed);
        recyclerView = view.findViewById(R.id.bedRecView);

        Log.e("====Services=====", String.valueOf(mList.size()));

        mAdapter =  new ServiceRecyclerAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new ServiceRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(
                        getActivity(),
                        mAdapter.getObjectList().get(position).getName(),
                        Toast.LENGTH_SHORT).show();
            }
        });

        searchBed.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                filter(editable.toString().trim());
            }
        });




        return view;
    }


    private void filter (String textString){
        List<Service> filteredList = new ArrayList<>();

        for(Service serviceItem: mList ) {
            Boolean nameMatches = serviceItem.getName().toLowerCase().contains(textString.toLowerCase());

            if (nameMatches){
                filteredList.add(serviceItem);
            }
        }

        mAdapter.filterList(filteredList);

    }

}