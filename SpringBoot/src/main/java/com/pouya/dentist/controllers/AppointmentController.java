package com.pouya.dentist.controllers;

import com.pouya.dentist.models.Appointment;
import com.pouya.dentist.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing appointments.
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    /**
     * Retrieves all appointments.
     * 
     * @return a list of all appointments
     */
    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentService.getAllAppointments();
    }

    /**
     * Retrieves an appointment by its ID.
     * 
     * @param id the ID of the appointment
     * @return the appointment with the specified ID
     */
    @GetMapping("/{id}")
    public Appointment getAppointmentById(@PathVariable Integer id) {
        return appointmentService.getAppointmentById(id);
    }

    /**
     * Creates a new appointment.
     * 
     * @param appointment the appointment to create
     * @return the created appointment
     */
    @PostMapping
    public Appointment createAppointment(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }

    /**
     * Updates an existing appointment.
     * 
     * @param id the ID of the appointment to update
     * @param appointment the updated appointment details
     * @return the updated appointment
     */
    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Integer id, @RequestBody Appointment appointment) {
        return appointmentService.updateAppointment(id, appointment);
    }

    /**
     * Deletes an appointment by its ID.
     * 
     * @param id the ID of the appointment to delete
     * @return a message indicating the result of the deletion
     */
    @DeleteMapping("/{id}")
    public String deleteAppointment(@PathVariable Integer id) {
       return appointmentService.deleteAppointment(id);
    }
}