package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pomden.apomden.Fragments.Routers.BedRouterFragment;
import com.pomden.apomden.MainDashboardScreen;
import com.pomden.apomden.Models.Bed;
import com.pomden.apomden.Models.Department;
import com.pomden.apomden.Models.Room;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;

import java.util.ArrayList;
import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class RoomAddNewFragment extends Fragment {

    private EditText getServiceNameText, getServiceDescriptionText;
    private Button btnAddService;
    private Spinner spinner;
    Room room = new Room();
    private static final String[] paths = {"item 1", "item 2", "item 3"};


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.room_add_new_fragment_layout, container, false);

        getServiceNameText = view.findViewById(R.id.serviceNameText);
        getServiceDescriptionText = view.findViewById(R.id.serviceDescriptionText);
        btnAddService = view.findViewById(R.id.btnAddService);

        btnAddService.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                // check if stuff isn't empty
                if (!checkEmptyness(getServiceNameText)){
                    String sName = getEtVal(getServiceNameText);
                    String sDesc = getEtVal(getServiceDescriptionText);

                    List<Bed> beds = new ArrayList<>();

                    room.setId(sName);
                    room.setSex(sDesc);
                    room.setBedArrayList(beds);



                    Globall.roomList.add(room);

                    ((BedRouterFragment)getParentFragment()).setViewPager(1);

                    Toast.makeText(
                            view.getContext(),
                            "Added Successfully",
                            Toast.LENGTH_SHORT).show();

                } else {
                    Toast.makeText(
                            view.getContext(),
                            "Enter A Value In The Name Field",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });

        spinner = view.findViewById(R.id.spinner);
        ArrayAdapter<Department> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Globall.departmentList );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                room.setDepartment(Globall.departmentList.get(i));

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                btnAddService.setEnabled(false);
            }
        });

        return view;
    }


    private boolean checkEmptyness (EditText editText) {
        return getEtVal(editText).isEmpty();
    }

    private String getEtVal (EditText editText) {
        return editText.getText().toString().trim();
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
}