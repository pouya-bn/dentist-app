package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Dentist;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Dentist entity.
 * This interface is used by the DentistService class to interact with the
 * Dentist entity in the database.
 */
public interface DentistRepository extends JpaRepository<Dentist, Integer> {
}
