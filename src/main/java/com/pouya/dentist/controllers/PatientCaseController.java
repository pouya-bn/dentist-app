package com.pouya.dentist.controllers;

import com.pouya.dentist.models.PatientCase;
import com.pouya.dentist.services.PatientCaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cases")
public class PatientCaseController {

    @Autowired
    private PatientCaseService patientCaseService;

    @GetMapping
    public List<PatientCase> getAllCases() {
        return patientCaseService.getAllCases();
    }

    @GetMapping("/{id}")
    public PatientCase getCaseById(@PathVariable Integer id) {
        return patientCaseService.getCaseById(id);
    }

    @PostMapping
    public PatientCase createCase(@RequestBody PatientCase patientCase) {
        return patientCaseService.createCase(patientCase);
    }

    @PutMapping("/{id}")
    public PatientCase updateCase(@PathVariable Integer id, @RequestBody PatientCase patientCase) {
        return patientCaseService.updateCase(id, patientCase);
    }

    @DeleteMapping("/{id}")
    public String deleteCase(@PathVariable Integer id) {
        try {
            patientCaseService.deleteCase(id);
            return "Case with id " + id + " deleted successfully";
        } catch (Exception e) {
            return "Error deleting case with id " + id;
        }
    }
}
