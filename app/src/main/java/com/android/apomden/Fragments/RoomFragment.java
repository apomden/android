package com.android.apomden.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.apomden.Adapters.DepartmentRecyclerAdapter;
import com.android.apomden.Adapters.RoomRecyclerAdapter;
import com.android.apomden.Models.Department;
import com.android.apomden.Models.Room;
import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoomFragment extends Fragment {

    EditText searchBed;
    private RecyclerView recyclerView;
    private RoomRecyclerAdapter mAdapter;
    private List<Room> mList;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.department_fragment, container, false);

        mList = Globall.roomList;
        searchBed = view.findViewById(R.id.searchBed);
        recyclerView = view.findViewById(R.id.bedRecView);


        mAdapter =  new RoomRecyclerAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new RoomRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(
                        getActivity(),
                        mAdapter.getObjectList().get(position).getId(),
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
        List<Room> filteredList = new ArrayList<>();

        for(Room deptItem: mList ) {
            boolean nameMatches = deptItem.getId().toLowerCase().contains(textString.toLowerCase());

            if (nameMatches){
                filteredList.add(deptItem);
            }
        }

        mAdapter.filterList(filteredList);

    }

}