package com.android.apomden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.apomden.R;
import com.android.apomden.Utilities.Globall;


/**
 * A placeholder fragment containing a simple view.
 */
public class ServicesAddNewFragment extends Fragment {


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.services_add_new_fragment_layout, container, false);


        return view;
    }
}