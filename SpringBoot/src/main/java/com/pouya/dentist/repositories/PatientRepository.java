package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
