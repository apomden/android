package com.android.apomden.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Department {
    private String id, name;
    private List<Room> roomArrayList;

    public Department(String id, String name, List<Room> roomArrayList) {
        this.id = id;
        this.name = name;
        this.roomArrayList = roomArrayList;
    }

    public Department(String id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Room> getRoomArrayList() {
        return roomArrayList;
    }

    public void setRoomArrayList(List<Room> roomArrayList) {
        this.roomArrayList = roomArrayList;
    }
}
