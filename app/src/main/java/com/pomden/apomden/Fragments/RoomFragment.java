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
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.pomden.apomden.Adapters.RoomRecyclerAdapter;
import com.pomden.apomden.Fragments.Routers.BedRouterFragment;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Models.Department;
import com.pomden.apomden.Models.Room;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoomFragment extends Fragment {
    public static RoomFragment self;
    EditText searchBed;
    private RecyclerView recyclerView;
    private RoomRecyclerAdapter mAdapter;
    private List<Room> mList;



    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {

        self = this;
        View view = inflater.inflate(R.layout.department_fragment, container, false);

        mList = Globall.roomList;


        Log.e("Mlist Size", "Mlist size  " + mList.size() );


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

                Room room = mAdapter.getObjectList().get(position);
                AllBedFragment.self.setMlist(room.getBedArrayList());
                BedRouterFragment.self.setViewPager(2);
                Globall.clickToPosition=1;
                Globall.clickFromPosition=1;
                Globall.sameSituationPosition=1;
//                Toast.makeText(getActivity(), room.getBedArrayList().size() + "", Toast.LENGTH_SHORT).show();
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
                    if (Globall.clickFromPosition == Globall.clickToPosition){
                        BedRouterFragment.self.setViewPager(Globall.sameSituationPosition);

                        Globall.clickFromPosition =1;
                        Globall.clickToPosition=1;
                        Globall.specificClickedBy=0;
                        Globall.sameSituationPosition=0;

                    } else {
                        MainDashboardScreen.self.setViewPager(Globall.clickFromPosition);

                        Globall.clickFromPosition =1;
                        Globall.clickToPosition=1;
                        Globall.specificClickedBy=0;
                        Globall.sameSituationPosition=0;
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void setMlist(List<Room> room){
        mAdapter.setRoomList(room);
    }





}