package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

/**
 * Entity representing a Dentist.
 * A dentist is a specialized user with a license number and a specialization field.
 * This entity is stored in the "dentists" table and uses "DENTIST" as its discriminator value.
 */
@Entity
@Table(name = "dentists")
@DiscriminatorValue("DENTIST")
public class Dentist extends User {
    private String licenseNumber;
    private String specialization;

    /**
     * Default constructor.
     */
    public Dentist() {
    }

    /**
     * Constructs a new Dentist with specified details.
     */
    public Dentist(String username, String email, String phone, String password, String licenseNumber, String specialization, LocalDate dateOfBirth) {
        super(username, email, phone, password, dateOfBirth);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
    }

    /**
     * Gets the license number of the dentist.
     *
     * @return the license number
     */
    @JsonProperty("license_number")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    /**
     * Sets the license number of the dentist.
     *
     * @param licenseNumber the new license number
     */
    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    /**
     * Gets the specialization of the dentist.
     *
     * @return the specialization
     */
    public String getSpecialization() {
        return specialization;
    }

    /**
     * Sets the specialization of the dentist.
     *
     * @param specialization the new specialization
     */
    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}