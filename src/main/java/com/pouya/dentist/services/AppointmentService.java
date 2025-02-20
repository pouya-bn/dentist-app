package com.pouya.dentist.services;

import com.pouya.dentist.exceptions.ResourceNotFoundException;
import com.pouya.dentist.models.Appointment;
import com.pouya.dentist.repositories.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Appointment getAppointmentById(Integer id) {
        return appointmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Appointment not found with id " + id));
    }

    public Appointment createAppointment(Appointment appointment) {
        if (appointment.getAppointmentTime() == null) {
            appointment.setAppointmentTime(LocalDateTime.now());
        }
        return appointmentRepository.save(appointment);
    }

    public Appointment updateAppointment(Integer id, Appointment appointmentDetails) {
        Appointment appointment = getAppointmentById(id);
        appointment.setAppointmentTime(appointmentDetails.getAppointmentTime());
        appointment.setStatus(appointmentDetails.getStatus());
        appointment.setDentist(appointmentDetails.getDentist());
        appointment.setPatient(appointmentDetails.getPatient());
        return appointmentRepository.save(appointment);
    }

    public void deleteAppointment(Integer id) {
        appointmentRepository.deleteById(id);
    }
}
