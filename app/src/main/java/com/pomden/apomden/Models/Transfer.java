package com.pomden.apomden.Models;

public class Transfer {

    private String age, gender, isEmergency, isConscious,
    diagnosisAndTreatmentGiven, immediateReasonForReferral, referringStaff,
    referringStaffEmail, _id, name;

    private Department originDepartment, destinationDepertment;
    private Hospital originFacility, destinationFacility;

    public Transfer() {
    }

    public Transfer(String age, String gender, String isEmergency, String isConscious, String diagnosisAndTreatmentGiven, String immediateReasonForReferral, String referringStaff, String referringStaffEmail, String _id, String name, Department originDepartment, Department destinationDepertment, Hospital originFacility, Hospital destinationFacility) {
        this.age = age;
        this.gender = gender;
        this.isEmergency = isEmergency;
        this.isConscious = isConscious;
        this.diagnosisAndTreatmentGiven = diagnosisAndTreatmentGiven;
        this.immediateReasonForReferral = immediateReasonForReferral;
        this.referringStaff = referringStaff;
        this.referringStaffEmail = referringStaffEmail;
        this._id = _id;
        this.name = name;
        this.originDepartment = originDepartment;
        this.destinationDepertment = destinationDepertment;
        this.originFacility = originFacility;
        this.destinationFacility = destinationFacility;
    }

    public Department getOriginDepartment() {
        return originDepartment;
    }

    public void setOriginDepartment(Department originDepartment) {
        this.originDepartment = originDepartment;
    }

    public Department getDestinationDepertment() {
        return destinationDepertment;
    }

    public void setDestinationDepertment(Department destinationDepertment) {
        this.destinationDepertment = destinationDepertment;
    }

    public Hospital getOriginFacility() {
        return originFacility;
    }

    public void setOriginFacility(Hospital originFacility) {
        this.originFacility = originFacility;
    }

    public Hospital getDestinationFacility() {
        return destinationFacility;
    }

    public void setDestinationFacility(Hospital destinationFacility) {
        this.destinationFacility = destinationFacility;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getIsEmergency() {
        return isEmergency;
    }

    public void setIsEmergency(String isEmergency) {
        this.isEmergency = isEmergency;
    }

    public String getIsConscious() {
        return isConscious;
    }

    public void setIsConscious(String isConscious) {
        this.isConscious = isConscious;
    }

    public String getDiagnosisAndTreatmentGiven() {
        return diagnosisAndTreatmentGiven;
    }

    public void setDiagnosisAndTreatmentGiven(String diagnosisAndTreatmentGiven) {
        this.diagnosisAndTreatmentGiven = diagnosisAndTreatmentGiven;
    }

    public String getImmediateReasonForReferral() {
        return immediateReasonForReferral;
    }

    public void setImmediateReasonForReferral(String immediateReasonForReferral) {
        this.immediateReasonForReferral = immediateReasonForReferral;
    }

    public String getReferringStaff() {
        return referringStaff;
    }

    public void setReferringStaff(String referringStaff) {
        this.referringStaff = referringStaff;
    }

    public String getReferringStaffEmail() {
        return referringStaffEmail;
    }

    public void setReferringStaffEmail(String referringStaffEmail) {
        this.referringStaffEmail = referringStaffEmail;
    }

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
