package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Treatment;
import com.pouya.dentist.services.TreatmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing treatments.
 */
@RestController
@RequestMapping("/api/treatments")
public class TreatmentController {

    @Autowired
    private TreatmentService treatmentService;

    /**
     * Retrieves all treatments.
     *
     * @return a list of all treatments
     */
    @GetMapping
    public List<Treatment> getAllTreatments() {
        return treatmentService.getAllTreatments();
    }

    /**
     * Retrieves a treatment by its ID.
     *
     * @param id the ID of the treatment
     * @return the treatment with the specified ID
     */
    @GetMapping("/{id}")
    public Treatment getTreatmentById(@PathVariable Integer id) {
        return treatmentService.getTreatmentById(id);
    }

    /**
     * Creates a new treatment.
     *
     * @param treatment the treatment to create
     * @return the created treatment
     */
    @PostMapping
    public Treatment createTreatment(@RequestBody Treatment treatment) {
        return treatmentService.createTreatment(treatment);
    }

    /**
     * Updates an existing treatment.
     *
     * @param id the ID of the treatment to update
     * @param treatment the updated treatment details
     * @return the updated treatment
     */
    @PutMapping("/{id}")
    public Treatment updateTreatment(@PathVariable Integer id, @RequestBody Treatment treatment) {
        return treatmentService.updateTreatment(id, treatment);
    }

    /**
     * Deletes a treatment by its ID.
     *
     * @param id the ID of the treatment to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public String deleteTreatment(@PathVariable Integer id) {
        return treatmentService.deleteTreatment(id);
    }
}