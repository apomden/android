package com.android.apomden.Models;

import java.util.ArrayList;

public class Room {
    private String sex, id;
    private ArrayList<Bed> bedArrayList;

    public Room(String sex, String id, ArrayList<Bed> bedArrayList) {
        this.sex = sex;
        this.id = id;
        this.bedArrayList = bedArrayList;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public ArrayList<Bed> getBedArrayList() {
        return bedArrayList;
    }

    public void setBedArrayList(ArrayList<Bed> bedArrayList) {
        this.bedArrayList = bedArrayList;
    }
}
