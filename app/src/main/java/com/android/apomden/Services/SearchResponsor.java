package com.android.apomden.Services;

import com.android.apomden.Models.Facility;

public interface SearchResponsor {


    void onSuccess (Facility facility);
    void onFailed (String string);
}
