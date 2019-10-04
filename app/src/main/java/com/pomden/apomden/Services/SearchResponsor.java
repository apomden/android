package com.pomden.apomden.Services;

import com.pomden.apomden.Models.Facility;

import java.util.List;

public interface SearchResponsor {


    void onSuccess (List<Facility> facilityList);
    void onFailed (String string);
}
