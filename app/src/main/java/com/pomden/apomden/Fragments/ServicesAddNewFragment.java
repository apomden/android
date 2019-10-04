package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.pomden.apomden.Fragments.Routers.ServicesRouterFragment;
import com.pomden.apomden.Models.Service;
import com.android.apomden.R;
import com.pomden.apomden.Utilities.Globall;


/**
 * A placeholder fragment containing a simple view.
 */
public class ServicesAddNewFragment extends Fragment {

    private EditText getServiceNameText, getServiceDescriptionText;
    private Button btnAddService;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_add_new_fragment_layout, container, false);

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

                    Globall.serviceList.add(new Service(sName, sDesc, "mongoDb"));

                    ((ServicesRouterFragment)getParentFragment()).setViewPager(0);

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

        return view;
    }


    private boolean checkEmptyness (EditText editText) {
        return getEtVal(editText).isEmpty();
    }

    private String getEtVal (EditText editText) {
        return editText.getText().toString().trim();
    }
}