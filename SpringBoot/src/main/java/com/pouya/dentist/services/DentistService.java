package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing dentists.
 */
@Service
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    /**
     * Retrieves all dentists.
     *
     * @return a list of all dentists
     */
    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    /**
     * Retrieves a dentist by ID.
     *
     * @param id the ID of the dentist to retrieve
     * @return the dentist with the specified ID
     * @throws ResourceNotFoundException if the dentist is not found
     */
    public Dentist getDentistById(Integer id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + id));
    }

    /**
     * Creates a new dentist.
     *
     * @param dentist the dentist to create
     * @return the created dentist
     */
    public Dentist createDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

    /**
     * Updates an existing dentist.
     *
     * @param id the ID of the dentist to update
     * @param dentistDetails the updated dentist details
     * @return the updated dentist
     * @throws ResourceNotFoundException if the dentist is not found
     */
    public Dentist updateDentist(Integer id, Dentist dentistDetails) {
        Dentist dentist = getDentistById(id);
        dentist.setUsername(dentistDetails.getUsername());
        dentist.setEmail(dentistDetails.getEmail());
        dentist.setPhone(dentistDetails.getPhone());
        dentist.setPassword(dentistDetails.getPassword());
        dentist.setDateOfBirth(dentistDetails.getDateOfBirth());
        dentist.setLicenseNumber(dentistDetails.getLicenseNumber());
        dentist.setSpecialization(dentistDetails.getSpecialization());
        return dentistRepository.save(dentist);
    }

    /**
     * Deletes a dentist by ID.
     *
     * @param id the ID of the dentist to delete
     * @return a message indicating the result of the deletion
     */
    public String deleteDentist(Integer id) {
        try {
            getDentistById(id);
            dentistRepository.deleteById(id);
            return "Dentist with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Dentist not found with id " + id;
            }
            return "Error deleting dentist with id " + id;
        }
    }
}