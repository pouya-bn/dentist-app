package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Entity representing a Patient.
 * A patient is a specialized user with an insurance number and an emergency contact.
 * This entity is stored in the "patients" table and uses "PATIENT" as its discriminator value.
 */
@Entity
@Table(name = "patients")
@DiscriminatorValue("PATIENT")
public class Patient extends User {

    /**
     * The insurance number of the patient.
     */
    private String insuranceNumber;

    /**
     * The emergency contact of the patient.
     */
    private String emergencyContact;

    /**
     * Default constructor.
     */
    public Patient() {
    }

    /**
     * Constructs a new Patient object with the given insurance number and emergency contact.
     */
    public Patient(String insuranceNumber, String emergencyContact) {
        super();
        this.insuranceNumber = insuranceNumber;
        this.emergencyContact = emergencyContact;
    }

    /**
     * Gets the insurance number of the patient.
     *
     * @return the insurance number of the patient
     */
    @JsonProperty("insurance_number")
    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    /**
     * Sets the insurance number of the patient.
     *
     * @param insuranceNumber the insurance number of the patient
     */
    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    /**
     * Gets the emergency contact of the patient.
     *
     * @return the emergency contact of the patient
     */
    @JsonProperty("emergency_contact")
    public String getEmergencyContact() {
        return emergencyContact;
    }

    /**
     * Sets the emergency contact of the patient.
     *
     * @param emergencyContact the emergency contact of the patient
     */
    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }
}