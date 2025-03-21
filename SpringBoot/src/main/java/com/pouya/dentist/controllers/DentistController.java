package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing dentists.
 */
@RestController
@RequestMapping("/api/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    /**
     * Returns a list of all dentists.
     *
     * @return a list of all dentists
     */
    @GetMapping
    public List<Dentist> getAllDentists() {
        return dentistService.getAllDentists();
    }

    /**
     * Returns the dentist with the given id.
     *
     * @param id the id of the dentist to return
     * @return the dentist with the given id
     */
    @GetMapping("/{id}")
    public Dentist getDentistById(@PathVariable Integer id) {
        return dentistService.getDentistById(id);
    }

    /**
     * Creates a new dentist.
     *
     * @param dentist the details of the dentist to create
     * @return the created dentist
     */
    @PostMapping
    public Dentist createDentist(@RequestBody Dentist dentist) {
        return dentistService.createDentist(dentist);
    }

    /**
     * Updates the dentist with the given id.
     *
     * @param id      the id of the dentist to update
     * @param dentist the details of the dentist to update
     * @return the updated dentist
     */
    @PutMapping("/{id}")
    public Dentist updateDentist(@PathVariable Integer id, @RequestBody Dentist dentist) {
        return dentistService.updateDentist(id, dentist);
    }

    /**
     * Deletes the dentist with the given id.
     *
     * @param id the id of the dentist to delete
     * @return a success message if the dentist is deleted successfully
     */
    @DeleteMapping("/{id}")
    public String deleteDentist(@PathVariable Integer id) {
        return dentistService.deleteDentist(id);
    }
}