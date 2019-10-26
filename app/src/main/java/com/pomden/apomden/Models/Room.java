package com.pomden.apomden.Models;

import java.util.List;

public class Room {
    private String sex, id;
    private List<Bed> bedArrayList;
    private Department department;
    private String name;


    public Room(String sex, String id, List<Bed> bedArrayList) {
        this.sex = sex;
        this.id = id;
        this.bedArrayList = bedArrayList;
    }

    public Room() {

    }

    public Room(String sex, String id, List<Bed> bedArrayList, Department department, String name) {
        this.sex = sex;
        this.id = id;
        this.bedArrayList = bedArrayList;
        this.department = department;
        this.name = name;
    }

    public Room(String sex, String id, List<Bed> bedArrayList, String name) {
        this.sex = sex;
        this.id = id;
        this.bedArrayList = bedArrayList;
        this.name = name;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
