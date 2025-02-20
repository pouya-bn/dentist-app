package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.services.DentistService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/dentists")
public class DentistController {

    @Autowired
    private DentistService dentistService;

    @GetMapping
    public List<Dentist> getAllDentists() {
        return dentistService.getAllDentists();
    }

    @GetMapping("/{id}")
    public Dentist getDentistById(@PathVariable Integer id) {
        return dentistService.getDentistById(id);
    }

    @PostMapping
    public Dentist createDentist(@RequestBody Dentist dentist) {
        return dentistService.createDentist(dentist);
    }

    @PutMapping("/{id}")
    public Dentist updateDentist(@PathVariable Integer id, @RequestBody Dentist dentist) {
        return dentistService.updateDentist(id, dentist);
    }

    @DeleteMapping("/{id}")
    public String deleteDentist(@PathVariable Integer id) {
        try {
            dentistService.deleteDentist(id);
            return "Dentist with id " + id + " deleted successfully";
        } catch (Exception e) {
            return "Error deleting dentist with id " + id;
        }
    }
}
