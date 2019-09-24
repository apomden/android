package com.android.apomden.Models;

public class User {
     String email, facility, password, domain;

    public User(String email, String facility, String password, String domain) {
        this.email = email;
        this.facility = facility;
        this.password = password;
        this.domain = domain;
    }

    public User(String email, String facility, String domain) {
        this.email = email;
        this.facility = facility;
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacility() {
        return facility;
    }

    public void setFacility(String facility) {
        this.facility = facility;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }
}
