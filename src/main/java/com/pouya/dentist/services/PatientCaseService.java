package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.PatientCase;
import com.pouya.dentist.repositories.PatientCaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PatientCaseService {

    @Autowired
    private PatientCaseRepository patientCaseRepository;

    public List<PatientCase> getAllCases() {
        return patientCaseRepository.findAll();
    }

    public PatientCase getCaseById(Integer id) {
        return patientCaseRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("PatientCase not found with id " + id));
    }

    public PatientCase createCase(PatientCase patientCase) {
        patientCase.setCreatedDate(LocalDateTime.now());
        return patientCaseRepository.save(patientCase);
    }

    public PatientCase updateCase(Integer id, PatientCase caseDetails) {
        PatientCase existingCase = getCaseById(id);
        existingCase.setTitle(caseDetails.getTitle());
        existingCase.setDescription(caseDetails.getDescription());
        existingCase.setStatus(caseDetails.getStatus());
        existingCase.setPatient(caseDetails.getPatient());
        existingCase.setDentist(caseDetails.getDentist());
        return patientCaseRepository.save(existingCase);
    }

    public void deleteCase(Integer id) {
        patientCaseRepository.deleteById(id);
    }
}
