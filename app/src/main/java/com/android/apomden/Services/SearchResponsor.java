package com.android.apomden.Services;

import com.android.apomden.Models.Facility;

import java.util.List;

public interface SearchResponsor {


    void onSuccess (List<Facility> facilityList);
    void onFailed (String string);
}
