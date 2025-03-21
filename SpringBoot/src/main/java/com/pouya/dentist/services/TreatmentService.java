package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.models.Patient;
import com.pouya.dentist.models.Treatment;
import com.pouya.dentist.repositories.DentistRepository;
import com.pouya.dentist.repositories.PatientRepository;
import com.pouya.dentist.repositories.TreatmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service class for managing treatments.
 */
@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    /**
     * Retrieves all treatments.
     *
     * @return a list of all treatments
     */
    @Transactional(readOnly = true)
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    /**
     * Retrieves a treatment by its ID.
     *
     * @param id the ID of the treatment
     * @return the treatment with the specified ID
     * @throws ResourceNotFoundException if the treatment is not found
     */
    @Transactional(readOnly = true)
    public Treatment getTreatmentById(Integer id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id " + id));
    }

    /**
     * Creates a new treatment.
     *
     * @param treatment the treatment to create
     * @return the created treatment
     * @throws IllegalArgumentException if {@code patient_id} or {@code dentist_id} is null
     * @throws ResourceNotFoundException if the patient or dentist is not found
     */
    @Transactional
    public Treatment createTreatment(Treatment treatment) {
        Integer patientId = treatment.getPatient_id();
        Integer dentistId = treatment.getDentist_id();

        if (patientId == null || dentistId == null) {
            throw new IllegalArgumentException("patient_id and dentist_id must be provided.");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
        Dentist dentist = dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + dentistId));

        treatment.setPatient(patient);
        treatment.setDentist(dentist);
        treatment.setCreatedDate(LocalDateTime.now());
        return treatmentRepository.save(treatment);
    }

    /**
     * Updates an existing treatment.
     *
     * @param id the ID of the treatment to update
     * @param treatment the updated treatment details
     * @return the updated treatment
     * @throws ResourceNotFoundException if the treatment is not found
     * @throws IllegalArgumentException if {@code patient_id} or {@code dentist_id} is null
     */
    @Transactional
    public Treatment updateTreatment(Integer id, Treatment treatment) {
        Treatment existingTreatment = getTreatmentById(id);
        existingTreatment.setTitle(treatment.getTitle());
        existingTreatment.setDescription(treatment.getDescription());
        existingTreatment.setStatus(treatment.getStatus());

        Integer patientId = treatment.getPatient_id();
        Integer dentistId = treatment.getDentist_id();

        if (patientId != null) {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
            existingTreatment.setPatient(patient);
        }

        if (dentistId != null) {
            Dentist dentist = dentistRepository.findById(dentistId)
                    .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + dentistId));
            existingTreatment.setDentist(dentist);
        }

        return treatmentRepository.save(existingTreatment);
    }

    /**
     * Deletes a treatment by its ID.
     *
     * @param id the ID of the treatment to delete
     * @return a message indicating the result of the deletion
     * @throws ResourceNotFoundException if the treatment is not found
     */
    @Transactional
    public String deleteTreatment(Integer id) {
        try {
            getTreatmentById(id);
            treatmentRepository.deleteById(id);
            return "Treatment with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Treatment not found with id " + id;
            }
            return "Error deleting treatment with id " + id;
        }
    }
}