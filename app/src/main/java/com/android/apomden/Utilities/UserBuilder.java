package com.android.apomden.Utilities;

import com.android.apomden.Models.User;

import java.util.HashMap;
import java.util.Map;

public class UserBuilder {

    public static Map<String, Object> buildUserJson(User user) {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("email", user.getEmail());
        map.put("password", user.getPassword());
        map.put("domain", user.getDomain());
        map.put("facility", user.getFacility());
        return map;
    }
}
