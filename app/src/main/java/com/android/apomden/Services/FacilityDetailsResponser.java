package com.android.apomden.Services;

import com.android.apomden.Models.Department;

import java.util.List;

public interface FacilityDetailsResponser {

    void onSuccess (List<Department> departments);
    void onFailed  (String string);
}
