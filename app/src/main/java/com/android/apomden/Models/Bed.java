package com.android.apomden.Models;

public class Bed {
    private Boolean isOccupied;
    private String id, name, status;

    public Bed(Boolean isOccupied, String id, String name, String status) {
        this.isOccupied = isOccupied;
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public Bed(Boolean isOccupied, String name, String status) {
        this.isOccupied = isOccupied;
        this.name = name;
        this.status = status;
    }

    public Bed() {

    }

    public Boolean getOccupied() {
        return isOccupied;
    }

    public void setOccupied(Boolean occupied) {
        isOccupied = occupied;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
