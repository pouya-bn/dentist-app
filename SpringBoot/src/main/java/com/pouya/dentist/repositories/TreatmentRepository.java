package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Treatment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Treatment entity.
 * This interface is used by the TreatmentService class to interact with the
 * Treatment entity in the database.
 */
public interface TreatmentRepository extends JpaRepository<Treatment, Integer> {
}
