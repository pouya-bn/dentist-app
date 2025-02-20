package com.pouya.dentist.models;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "patients")
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    private String insuranceNumber;
    private String emergencyContact;

    public Patient() {
        super();
    }

    public Patient(String insuranceNumber, String emergencyContact) {
        super();
        this.insuranceNumber = insuranceNumber;
        this.emergencyContact = emergencyContact;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}
