package com.android.apomden.Utilities;

import com.android.apomden.Models.Facility;

import java.util.HashMap;
import java.util.Map;

public class UserBuilder {

    public static Map<String, Object> buildUserJson(Facility facility) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", facility.getEmail());
        map.put("password", facility.getPassword());
        map.put("domain", facility.getDomain());
        map.put("facility", facility.getFacility());
        return map;
    }
}
