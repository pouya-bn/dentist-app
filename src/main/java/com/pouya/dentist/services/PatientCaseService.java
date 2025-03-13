package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.models.Patient;
import com.pouya.dentist.models.PatientCase;
import com.pouya.dentist.repositories.DentistRepository;
import com.pouya.dentist.repositories.PatientCaseRepository;
import com.pouya.dentist.repositories.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientCaseService {

    @Autowired
    private PatientCaseRepository patientCaseRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DentistRepository dentistRepository;

    @Transactional(readOnly = true)
    public List<PatientCase> getAllCases() {
        return patientCaseRepository.findAll();
    }

    @Transactional(readOnly = true)
    public PatientCase getCaseById(Integer id) {
        return patientCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PatientCase not found with id " + id));
    }

    @Transactional
    public PatientCase createCase(PatientCase patientCase) {
        Integer patientId = patientCase.getPatient_id();
        Integer dentistId = patientCase.getDentist_id();

        if (patientId == null || dentistId == null) {
            throw new IllegalArgumentException("patient_id and dentist_id must be provided.");
        }

        Patient patient = patientRepository.findById(patientId)
                .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
        Dentist dentist = dentistRepository.findById(dentistId)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + dentistId));

        patientCase.setPatient(patient);
        patientCase.setDentist(dentist);
        patientCase.setCreatedDate(LocalDateTime.now());
        return patientCaseRepository.save(patientCase);
    }

    @Transactional
    public PatientCase updateCase(Integer id, PatientCase caseDetails) {
        PatientCase existingCase = getCaseById(id);
        existingCase.setTitle(caseDetails.getTitle());
        existingCase.setDescription(caseDetails.getDescription());
        existingCase.setStatus(caseDetails.getStatus());

        Integer patientId = caseDetails.getPatient_id();
        Integer dentistId = caseDetails.getDentist_id();

        if (patientId != null) {
            Patient patient = patientRepository.findById(patientId)
                    .orElseThrow(() -> new ResourceNotFoundException("Patient not found with id " + patientId));
            existingCase.setPatient(patient);
        }

        if (dentistId != null) {
            Dentist dentist = dentistRepository.findById(dentistId)
                    .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + dentistId));
            existingCase.setDentist(dentist);
        }

        return patientCaseRepository.save(existingCase);
    }

    @Transactional
    public void deleteCase(Integer id) {
        patientCaseRepository.deleteById(id);
    }
}