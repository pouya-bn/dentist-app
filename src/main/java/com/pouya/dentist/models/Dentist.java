package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "dentists")
@DiscriminatorValue("DENTIST")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Dentist extends User {
    private String licenseNumber;
    private String specialization;

    public Dentist() {
        super();
    }

    public Dentist(String username, String email, String phone, String password, String licenseNumber, String specialization, LocalDate dateOfBirth) {
        super(username, email, phone, password, dateOfBirth);
        this.licenseNumber = licenseNumber;
        this.specialization = specialization;
    }

    @JsonProperty("license_number")
    public String getLicenseNumber() {
        return licenseNumber;
    }

    public void setLicenseNumber(String licenseNumber) {
        this.licenseNumber = licenseNumber;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specialization) {
        this.specialization = specialization;
    }
}