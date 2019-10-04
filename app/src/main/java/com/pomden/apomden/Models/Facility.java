package com.pomden.apomden.Models;

public class Facility {

    private  String  email,
             facilityId,
             password,
             domain,
             facilityName,
             facilityCountry=null,
             facilityCity=null,
             facilityRegion=null,
             facilityStreet=null,
             facilityDistrict=null;

    private  Boolean verified;

   public Facility () {

    }

    public Facility(String email, String facilityId, String domain, String facilityName, String facilityCountry, String facilityCity, String facilityRegion, String facilityStreet, String facilityDistrict, Boolean verified) {
        this.email = email;
        this.facilityId = facilityId;
        this.domain = domain;
        this.facilityName = facilityName;
        this.facilityCountry = facilityCountry;
        this.facilityCity = facilityCity;
        this.facilityRegion = facilityRegion;
        this.facilityStreet = facilityStreet;
        this.facilityDistrict = facilityDistrict;
        this.verified = verified;
    }

    public Facility(String email, String facilityId, String password, String domain) {
        this.email = email;
        this.facilityId = facilityId;
        this.password = password;
        this.domain = domain;
    }

    public Facility(String email, String facilityId, String domain) {
        this.email = email;
        this.facilityId = facilityId;
        this.domain = domain;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFacilityId() {
        return facilityId;
    }

    public void setFacilityId(String facilityId) {
        this.facilityId = facilityId;
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

    public String getFacilityName() {
        return facilityName;
    }

    public void setFacilityName(String facilityName) {
        this.facilityName = facilityName;
    }

    public String getFacilityCountry() {
        return facilityCountry;
    }

    public void setFacilityCountry(String facilityCountry) {
        this.facilityCountry = facilityCountry;
    }

    public String getFacilityCity() {
        return facilityCity;
    }

    public void setFacilityCity(String facilityCity) {
        this.facilityCity = facilityCity;
    }

    public String getFacilityRegion() {
        return facilityRegion;
    }

    public void setFacilityRegion(String facilityRegion) {
        this.facilityRegion = facilityRegion;
    }

    public String getFacilityStreet() {
        return facilityStreet;
    }

    public void setFacilityStreet(String facilityStreet) {
        this.facilityStreet = facilityStreet;
    }

    public String getFacilityDistrict() {
        return facilityDistrict;
    }

    public void setFacilityDistrict(String facilityDistrict) {
        this.facilityDistrict = facilityDistrict;
    }

    public Boolean getVerified() {
        return verified;
    }

    public void setVerified(Boolean verified) {
        this.verified = verified;
    }

    public String getFacilityAddress () {
        return this.facilityCity + ", " + this.facilityCountry + "  " + this.facilityDistrict + "  " + this.facilityStreet;
    }
}
