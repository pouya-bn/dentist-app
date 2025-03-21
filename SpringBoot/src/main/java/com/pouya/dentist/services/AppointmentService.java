package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Appointment;
import com.pouya.dentist.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

/**
 * Service for managing appointments.
 */
@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Retrieves all appointments.
     *
     * @return a list of all appointments
     */
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    /**
     * Retrieves an appointment by its ID.
     *
     * @param id the ID of the appointment
     * @return the appointment with the specified ID
     * @throws ResourceNotFoundException if the appointment with the specified ID does not exist
     */
    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
    }

    /**
     * Creates a new appointment.
     *
     * @param appointment the appointment to create
     * @return the created appointment
     */
    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getTime() == null) {
            appointment.setTime(LocalDateTime.now());
        }
        return appointmentRepository.save(appointment);
    }

    /**
     * Updates an existing appointment.
     *
     * @param id the ID of the appointment to update
     * @param appointmentDetails the updated appointment details
     * @return the updated appointment
     * @throws ResourceNotFoundException if the appointment with the specified ID does not exist
     */
    public Appointment updateAppointment(Integer id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);
        appointment.setTime(appointmentDetails.getTime());
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setDentistId(appointmentDetails.getDentistId());
        appointment.setPatientId(appointmentDetails.getPatientId());
        return appointmentRepository.save(appointment);
    }

    /**
     * Deletes an appointment by its ID.
     *
     * @param id the ID of the appointment to delete
     * @return a message indicating the result of the deletion
     * @throws ResourceNotFoundException if the appointment with the specified ID does not exist
     */
    public String deleteAppointment(Integer id) {
        try {
            getAppointmentById(id);
            appointmentRepository.deleteById(id);
            return "Appointment with id " + id + " deleted successfully";
        } catch (Exception e) {
            if (e instanceof ResourceNotFoundException) {
                return "Appointment not found with id " + id;
            }
            return "Error deleting appointment with id " + id;
        }
    }
}