package com.pouya.dentist.repositories;

import com.pouya.dentist.models.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * This interface extends the JpaRepository interface to provide the basic
 * CRUD operations for the Appointment entity.
 * This interface is used by the AppointmentService class to interact with the
 * Appointment entity in the database.
 */
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {
}
