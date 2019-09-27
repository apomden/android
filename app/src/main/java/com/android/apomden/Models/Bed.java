package com.android.apomden.Models;

public class Bed {
    private Boolean isOccupied;
    private String name, status;

    public Bed(Boolean isOccupied, String name, String status) {
        this.isOccupied = isOccupied;
        this.name = name;
        this.status = status;
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
}
