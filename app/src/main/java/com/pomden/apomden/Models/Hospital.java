package com.pomden.apomden.Models;

public class Hospital {

    private String  id, name, domain, type;

    public Hospital(String id, String name, String domain, String type) {
        this.id = id;
        this.name = name;
        this.domain = domain;
        this.type = type;
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

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
