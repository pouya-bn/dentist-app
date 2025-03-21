package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Patient entity.
 * This interface is used by the PatientService class to interact with the
 * Patient entity in the database.
 */
public interface PatientRepository extends JpaRepository<Patient, Integer> {
}
