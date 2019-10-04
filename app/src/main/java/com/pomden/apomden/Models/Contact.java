package com.pomden.apomden.Models;

import java.util.ArrayList;

public class Contact {

    private ArrayList<String> otherPhoneNumbers, otherEmails;
    private String id, primaryPhoneNumber, primaryEmail, secondaryPhoneNumber, secondaryEmail;

    public Contact(String id, String primaryPhoneNumber, String primaryEmail, String secondaryPhoneNumber, String secondaryEmail) {
        this.id = id;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.primaryEmail = primaryEmail;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        this.secondaryEmail = secondaryEmail;
    }

    public Contact(ArrayList<String> otherPhoneNumbers, ArrayList<String> otherEmails, String id, String primaryPhoneNumber, String primaryEmail, String secondaryPhoneNumber, String secondaryEmail) {
        this.otherPhoneNumbers = otherPhoneNumbers;
        this.otherEmails = otherEmails;
        this.id = id;
        this.primaryPhoneNumber = primaryPhoneNumber;
        this.primaryEmail = primaryEmail;
        this.secondaryPhoneNumber = secondaryPhoneNumber;
        this.secondaryEmail = secondaryEmail;
    }

    public ArrayList<String> getOtherPhoneNumbers() {
        return otherPhoneNumbers;
    }

    public void setOtherPhoneNumbers(ArrayList<String> otherPhoneNumbers) {
        this.otherPhoneNumbers = otherPhoneNumbers;
    }

    public ArrayList<String> getOtherEmails() {
        return otherEmails;
    }

    public void setOtherEmails(ArrayList<String> otherEmails) {
        this.otherEmails = otherEmails;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrimaryPhoneNumber() {
        return primaryPhoneNumber;
    }

    public void setPrimaryPhoneNumber(String primaryPhoneNumber) {
        this.primaryPhoneNumber = primaryPhoneNumber;
    }

    public String getPrimaryEmail() {
        return primaryEmail;
    }

    public void setPrimaryEmail(String primaryEmail) {
        this.primaryEmail = primaryEmail;
    }

    public String getSecondaryPhoneNumber() {
        return secondaryPhoneNumber;
    }

    public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
        this.secondaryPhoneNumber = secondaryPhoneNumber;
    }

    public String getSecondaryEmail() {
        return secondaryEmail;
    }

    public void setSecondaryEmail(String secondaryEmail) {
        this.secondaryEmail = secondaryEmail;
    }
}
