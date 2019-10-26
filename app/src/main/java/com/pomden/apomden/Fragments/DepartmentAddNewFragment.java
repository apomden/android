package com.pomden.apomden.Fragments;

import android.os.Bundle;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

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
public class DepartmentAddNewFragment extends Fragment {

    private EditText getServiceNameText, getServiceDescriptionText;
    private Button btnAddService;

    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.department_add_new_fragment_layout, container, false);

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

                    List<Room> beds = new ArrayList<>();


                    Globall.departmentList.add(new Department(sName, sDesc ,beds));

                    ((BedRouterFragment)getParentFragment()).setViewPager(0);

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