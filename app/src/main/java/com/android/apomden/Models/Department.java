package com.android.apomden.Models;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Department {
    private String id, name;
    private ArrayList<Room> roomArrayList;

    public Department(String id, String name, ArrayList<Room> roomArrayList) {
        this.id = id;
        this.name = name;
        this.roomArrayList = roomArrayList;
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

    public ArrayList<Room> getRoomArrayList() {
        return roomArrayList;
    }

    public void setRoomArrayList(ArrayList<Room> roomArrayList) {
        this.roomArrayList = roomArrayList;
    }
}
