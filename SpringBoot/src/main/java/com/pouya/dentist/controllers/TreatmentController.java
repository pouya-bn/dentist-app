package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Treatment;
import com.pouya.dentist.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    @GetMapping("/{id}")
    public Treatment getTreatmentById(@PathVariable Integer id) {
        return treatmentService.getTreatmentById(id);
    }

    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentService.createTreatment(treatment);
    }

    @PutMapping("/{id}")
    public Treatment updateTreatment(@PathVariable Integer id, @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment);
    }

    @DeleteMapping("/{id}")
    public String deleteTreatment(@PathVariable Integer id) {
        return treatmentService.deleteTreatment(id);
    }
}
