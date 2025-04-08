package co2124.part1.controller;

import co2124.part1.domain.AppointmentCreationDTO;
import co2124.part1.entities.Appointment;
import co2124.part1.entities.MedicalRecord;
import co2124.part1.respos.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentRestController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public Appointment addAppointment(AppointmentCreationDTO appointment) {
        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentDate(appointment.getAppointmentDate());



        return appointmentRepository.save(appointment);
    }

    @GetMapping("/{id}")
    public Appointment getAppointmentById(Long id) {
        return appointmentRepository.findAppointmentById(id);
    }

    @PutMapping("/{id}")
    public Appointment updateAppointment(@PathVariable Long id, @RequestBody Appointment appointment) {
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        if (existingAppointment != null) {
            existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
            existingAppointment.setDoctor(appointment.getDoctor());
            existingAppointment.setPatient(appointment.getPatient());
            return appointmentRepository.save(existingAppointment);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteAppointment(@PathVariable Long id) {
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        if (existingAppointment != null) {
            appointmentRepository.delete(existingAppointment);
        }
    }

    @GetMapping("/{id}/medical-record")
    public MedicalRecord getMedicalRecordByAppointmentId(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment != null) {
            return appointment.getMedicalRecord();
        }
        return null;
    }
}
