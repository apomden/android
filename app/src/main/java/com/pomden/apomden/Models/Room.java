package com.pomden.apomden.Models;

import java.util.List;

public class Room {
    private String sex, id;
    private List<Bed> bedArrayList;
    private Department department;


    public Room(String sex, String id, List<Bed> bedArrayList) {
        this.sex = sex;
        this.id = id;
        this.bedArrayList = bedArrayList;
    }

    public Room() {

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


    public List<Bed> getBedArrayList() {
        return bedArrayList;
    }

    public void setBedArrayList(List<Bed> bedArrayList) {
        this.bedArrayList = bedArrayList;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return id;
    }
}
