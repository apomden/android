package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pomden.apomden.Adapters.DepartmentRecyclerAdapter;
import com.pomden.apomden.Fragments.Routers.BedRouterFragment;
import com.pomden.apomden.Models.Department;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class DepartmentFragment extends Fragment {

    EditText searchBed;
    private RecyclerView recyclerView;
    private DepartmentRecyclerAdapter mAdapter;
    private List<Department> mList;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.department_fragment, container, false);

        mList = Globall.departmentList;
        searchBed = view.findViewById(R.id.searchBed);
        recyclerView = view.findViewById(R.id.bedRecView);

        mAdapter =  new DepartmentRecyclerAdapter(mList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getContext());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        mAdapter.notifyDataSetChanged();

        mAdapter.setOnItemClickListener(new DepartmentRecyclerAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Department dept = mAdapter.getObjectList().get(position);
                Log.e("DEPARTMENT_CLICK_ROOMS", String.valueOf(dept.getRoomArrayList().size()));
                Log.e("DEPARTMENT_NAME", String.valueOf(dept.getName()));
                Globall.clickToPosition=1;
                Globall.clickFromPosition=1;
                Globall.sameSituationPosition=0;
                RoomFragment.self.setMlist(dept.getRoomArrayList());
                BedRouterFragment.self.setViewPager(1);

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
        List<Department> filteredList = new ArrayList<>();

        for(Department deptItem: mList ) {
            boolean nameMatches = deptItem.getName().toLowerCase().contains(textString.toLowerCase());

            if (nameMatches){
                filteredList.add(deptItem);
            }
        }

        mAdapter.filterList(filteredList);

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