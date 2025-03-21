package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents an Appointment entity which is a booking for a dentist and a patient.
 * Each appointment contains a timestamp, a status, and IDs of the dentist and the patient.
 */
@Entity
@Table(name = "appointments")
public class Appointment {
    /**
     * The unique identifier of the appointment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The timestamp of the appointment.
     */
    private LocalDateTime time;

    /**
     * The status of the appointment.
     */
    private String status;

    /**
     * The ID of the dentist.
     */
    @Column(name = "dentist_id")
    private Integer dentistId;

    /**
     * The ID of the patient.
     */
    @Column(name = "patient_id")
    private Integer patientId;

    /**
     * Default constructor.
     */
    public Appointment() {
    }

    /**
     * Constructs an appointment with the given time, status, dentist ID, and patient ID.
     */
    public Appointment(LocalDateTime time, String status, Integer dentistId, Integer patientId) {
        this.time = time;
        this.status = status;
        this.dentistId = dentistId;
        this.patientId = patientId;
    }

    /**
     * Returns the unique identifier of the appointment.
     *
     * @return the ID of the appointment
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the appointment.
     *
     * @param id the new ID of the appointment
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the timestamp of the appointment.
     *
     * @return the timestamp of the appointment
     */
    public LocalDateTime getTime() {
        return time;
    }

    /**
     * Sets the timestamp of the appointment.
     *
     * @param time the new timestamp of the appointment
     */
    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    /**
     * Returns the status of the appointment.
     *
     * @return the status of the appointment
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the appointment.
     *
     * @param status the new status of the appointment
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the ID of the dentist.
     *
     * @return the ID of the dentist
     */
    @JsonProperty("dentist_id")
    public Integer getDentistId() {
        return dentistId;
    }

    /**
     * Sets the ID of the dentist.
     *
     * @param dentistId the new ID of the dentist
     */
    public void setDentistId(Integer dentistId) {
        this.dentistId = dentistId;
    }

    /**
     * Returns the ID of the patient.
     *
     * @return the ID of the patient
     */
    @JsonProperty("patient_id")
    public Integer getPatientId() {
        return patientId;
    }

    /**
     * Sets the ID of the patient.
     *
     * @param patientId the new ID of the patient
     */
    public void setPatientId(Integer patientId) {
        this.patientId = patientId;
    }
}