package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Patient;
import com.pouya.dentist.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing patients.
 */
@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    /**
     * Handles GET requests for all patients.
     * 
     * @return A list of all patients.
     */
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    /**
     * Handles GET requests for a patient with a given ID.
     * 
     * @param id The ID of the patient to get.
     * @return The patient with the given ID if it exists, null otherwise.
     */
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable Integer id) {
        return patientService.getPatientById(id);
    }

    /**
     * Handles POST requests to create a new patient.
     * 
     * @param patient The patient object to create.
     * @return The created patient.
     */
    @PostMapping
    public Patient createPatient(@RequestBody Patient patient) {
        return patientService.createPatient(patient);
    }

    /**
     * Handles PUT requests to update an existing patient.
     * 
     * @param id The ID of the patient to update.
     * @param patient The updated patient object.
     * @return The updated patient.
     */
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Integer id, @RequestBody Patient patient) {
        return patientService.updatePatient(id, patient);
    }

    /**
     * Handles DELETE requests to delete a patient.
     * 
     * @param id The ID of the patient to delete.
     * @return A string indicating whether the patient was deleted successfully or not.
     */
    @DeleteMapping("/{id}")
    public String deletePatient(@PathVariable Integer id) {
        return patientService.deletePatient(id);
    }
}