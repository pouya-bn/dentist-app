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

@Service
public class TreatmentService {

    @Autowired
    private TreatmentRepository treatmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Transactional(readOnly = true)
    public List<Treatment> getAllTreatments() {
        return treatmentRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Treatment getTreatmentById(Integer id) {
        return treatmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Treatment not found with id " + id));
    }

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