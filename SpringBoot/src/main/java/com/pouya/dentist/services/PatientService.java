package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Patient;
import com.pouya.dentist.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public Patient getPatientById(Integer id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + id));
    }

    public Patient createPatient(Patient patient) {
        return patientRepository.save(patient);
    }

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
