package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Dentist;
import com.pouya.dentist.repositories.DentistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DentistService {

    @Autowired
    private DentistRepository dentistRepository;

    public List<Dentist> getAllDentists() {
        return dentistRepository.findAll();
    }

    public Dentist getDentistById(Integer id) {
        return dentistRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Dentist not found with id " + id));
    }

    public Dentist createDentist(Dentist dentist) {
        return dentistRepository.save(dentist);
    }

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

    public void deleteDentist(Integer id) {
        dentistRepository.deleteById(id);
    }
}
