package com.pouya.dentist.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.time.LocalDateTime;

/**
 * Represents a Treatment entity which is linked to a patient and a dentist.
 * Each treatment contains a title, description, status, and metadata about its creation.
 */
@Entity
@Table(name = "treatments")
public class Treatment {
    /**
     * Unique identifier for the treatment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * Title of the treatment.
     */
    private String title;

    /**
     * Description of the treatment.
     */
    private String description;

    /**
     * Status of the treatment.
     */
    private String status;

    /**
     * Date and time when the treatment was created.
     */
    private LocalDateTime createdDate;

    /**
     * Patient linked to the treatment.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    /**
     * Dentist linked to the treatment.
     */
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "dentist_id")
    private Dentist dentist;

    /**
     * Patient ID of the treatment. This is a transient field which is not persisted in the database.
     */
    @Transient
    private Integer patient_id;

    /**
     * Dentist ID of the treatment. This is a transient field which is not persisted in the database.
     */
    @Transient
    private Integer dentist_id;

    /**
     * Default constructor.
     */
    public Treatment() {
    }

    /**
     * Constructor with all fields.
     */
    public Treatment(String title, String description, String status, LocalDateTime createdDate, Integer patient_id, Integer dentist_id) {
        this.title = title;
        this.description = description;
        this.status = status;
        this.createdDate = createdDate;
        this.patient_id = patient_id;
        this.dentist_id = dentist_id;
    }

    /**
     * Returns the unique identifier of the treatment.
     *
     * @return the ID of the treatment
     */
    public Integer getId() {
        return id;
    }

    /**
     * Sets the unique identifier of the treatment.
     *
     * @param id the new ID of the treatment
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * Returns the title of the treatment.
     *
     * @return the title of the treatment
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets the title of the treatment.
     *
     * @param title the new title of the treatment
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Returns the description of the treatment.
     *
     * @return the description of the treatment
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of the treatment.
     *
     * @param description the new description of the treatment
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the status of the treatment.
     *
     * @return the status of the treatment
     */
    public String getStatus() {
        return status;
    }

    /**
     * Sets the status of the treatment.
     *
     * @param status the new status of the treatment
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * Returns the date and time when the treatment was created.
     *
     * @return the created date of the treatment
     */
    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    /**
     * Sets the date and time when the treatment was created.
     *
     * @param createdDate the new created date of the treatment
     */
    public void setCreatedDate(LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }

    /**
     * Returns the patient linked to the treatment.
     *
     * @return the patient of the treatment
     */
    public Patient getPatient() {
        return patient;
    }

    /**
     * Sets the patient linked to the treatment.
     *
     * @param patient the new patient of the treatment
     */
    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    /**
     * Returns the dentist linked to the treatment.
     *
     * @return the dentist of the treatment
     */
    public Dentist getDentist() {
        return dentist;
    }

    /**
     * Sets the dentist linked to the treatment.
     *
     * @param dentist the new dentist of the treatment
     */
    public void setDentist(Dentist dentist) {
        this.dentist = dentist;
    }

    /**
     * Returns the patient ID of the treatment. This is a transient field which is not persisted in the database.
     *
     * @return the patient ID of the treatment
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Integer getPatient_id() {
        return patient_id;
    }

    /**
     * Sets the patient ID of the treatment. This is a transient field which is not persisted in the database.
     *
     * @param patient_id the new patient ID of the treatment
     */
    public void setPatient_id(Integer patient_id) {
        this.patient_id = patient_id;
    }

    /**
     * Returns the dentist ID of the treatment. This is a transient field which is not persisted in the database.
     *
     * @return the dentist ID of the treatment
     */
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    public Integer getDentist_id() {
        return dentist_id;
    }

    /**
     * Sets the dentist ID of the treatment. This is a transient field which is not persisted in the database.
     *
     * @param dentist_id the new dentist ID of the treatment
     */
    public void setDentist_id(Integer dentist_id) {
        this.dentist_id = dentist_id;
    }
}