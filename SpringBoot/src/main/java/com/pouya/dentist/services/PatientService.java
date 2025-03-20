package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Patient;
import com.pouya.dentist.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing patients.
 */
@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    /**
     * Retrieves a list of all patients.
     *
     * @return a list of all patients
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id the ID of the patient to retrieve
     * @return the patient with the given ID
     * @throws ResourceNotFoundException if the patient with the given ID does not exist
     */
    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    /**
     * Creates a new patient.
     *
     * @param patient the patient to create
     * @return the created patient
     */
    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Updates an existing patient.
     *
     * @param id the ID of the patient to update
     * @param patientDetails the updated patient details
     * @return the updated patient
     * @throws ResourceNotFoundException if the patient with the given ID does not exist
     */
    public Patient updatePatient(Integer id, Patient patientDetails) {
        Patient patient = getPatientById(id);
        patient.setUsername(patientDetails.getUsername());
        patient.setEmail(patientDetails.getEmail());
        patient.setPhone(patientDetails.getPhone());
        patient.setPassword(patientDetails.getPassword());
        patient.setDateOfBirth(patientDetails.getDateOfBirth());
        patient.setInsuranceNumber(patientDetails.getInsuranceNumber());
        patient.setEmergencyContact(patientDetails.getEmergencyContact());
        return patientRepository.save(patient);
    }

    /**
     * Deletes a patient.
     *
     * @param id the ID of the patient to delete
     * @return a string indicating whether the patient was deleted successfully or not
     * @throws ResourceNotFoundException if the patient with the given ID does not exist
     */
    public String deletePatient(Integer id) {
        try {
            getPatientById(id);
            patientRepository.deleteById(id);
            return "Patient with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Patient not found with id " + id;
            }
            return "Error deleting patient with id " + id;
        }
    }
}