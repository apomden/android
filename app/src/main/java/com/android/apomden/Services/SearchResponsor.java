package com.android.apomden.Services;

import com.android.apomden.Models.User;

public interface SearchResponsor {


    void onSuccess (User user);
    void onFailed (String string);
}
