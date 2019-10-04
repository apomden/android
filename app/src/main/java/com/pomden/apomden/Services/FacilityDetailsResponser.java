package com.pomden.apomden.Services;

import com.pomden.apomden.Models.Department;

import java.util.List;

public interface FacilityDetailsResponser {

    void onSuccess (List<Department> departments);
    void onFailed  (String string);
}
