package com.android.apomden.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.android.apomden.R;


/**
 * A placeholder fragment containing a simple view.
 */
public class SummaryFragment extends Fragment {
    TextView textView;


    @Override
    public View onCreateView(
            @NonNull LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.summary_fragment, container, false);
        textView = view.findViewById(R.id.section_label);

        return view;
    }
}