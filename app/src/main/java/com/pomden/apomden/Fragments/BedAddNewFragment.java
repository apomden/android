package com.pomden.apomden.Fragments;

import android.os.Bundle;
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
import com.pomden.apomden.Models.Bed;
import com.pomden.apomden.Models.Room;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;


/**
 * A placeholder fragment containing a simple view.
 */
public class BedAddNewFragment extends Fragment {

    private EditText getServiceNameText, getServiceDescriptionText;
    private Button btnAddService;
    private Spinner spinner;
    Bed room = new Bed();


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.bed_add_new_fragment_layout, container, false);

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


                    room.setId(sName);
                    room.setSex(sDesc);
                    room.setStatus("AVAILABLE");
                    room.setOccupied(false);
                    room.setName(sName);


                    Globall.bedList.add(room);

                    ((BedRouterFragment)getParentFragment()).setViewPager(2);

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
        ArrayAdapter<Room> adapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_spinner_item, Globall.roomList );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                room.setRoomName(Globall.roomList.get(i).getId());
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
}