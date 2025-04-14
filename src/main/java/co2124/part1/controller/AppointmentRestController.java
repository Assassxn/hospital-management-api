package co2124.part1.controller;

import co2124.part1.domain.AppointmentCreationDTO;
import co2124.part1.domain.AppointmentUpdateDTO;
import co2124.part1.entities.Appointment;
import co2124.part1.entities.Doctor;
import co2124.part1.entities.MedicalRecord;
import co2124.part1.entities.Patient;
import co2124.part1.enums.AppointmentStatus;
import co2124.part1.exception.CustomValidationErrorResponse;
import co2124.part1.respos.AppointmentRepository;
import co2124.part1.respos.DoctorRepository;
import co2124.part1.respos.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentRestController {
    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Appointment> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    @PostMapping
    public ResponseEntity<Appointment> addAppointment(@Valid @RequestBody AppointmentCreationDTO appointment) {
        Appointment newAppointment = new Appointment();
        newAppointment.setAppointmentDate(appointment.getAppointmentDate());
        newAppointment.setNotes(appointment.getNotes());
        newAppointment.setStatus(String.valueOf(AppointmentStatus.SCHEDULED)); // since we're creating a new appointment, set status to SCHEDULED

        // no need to validate patientId as it is already validated in the DTO
        Patient patient = patientRepository.findPatientById(appointment.getPatientId());
        newAppointment.setPatient(patient);

        // no need to validate doctorId as it is already validated in the DTO
        Doctor doctor = doctorRepository.findDoctorById(appointment.getDoctorId());
        newAppointment.setDoctor(doctor);
        Appointment createdAppointment = appointmentRepository.save(newAppointment);
        return ResponseEntity.created(null).body(createdAppointment);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAppointmentById(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);

        if (appointment == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No Appointment exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appointment);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateAppointment(@PathVariable Long id, @Valid @RequestBody AppointmentUpdateDTO appointment) {
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        if (existingAppointment == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No Appointment exists with ID: " + id)
            );

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }


        Doctor doctor = doctorRepository.findDoctorById(appointment.getDoctorId());
        Patient patient = patientRepository.findPatientById(appointment.getPatientId());

        existingAppointment.setDoctor(doctor);
        existingAppointment.setPatient(patient);
        existingAppointment.setNotes(appointment.getNotes());
        existingAppointment.setAppointmentDate(appointment.getAppointmentDate());
        existingAppointment.setStatus(appointment.getStatus());

        Appointment newAppointment = appointmentRepository.save(existingAppointment);
        return ResponseEntity.ok(newAppointment);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAppointment(@PathVariable Long id) {
        Appointment existingAppointment = appointmentRepository.findAppointmentById(id);
        if (existingAppointment == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No Appointment exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        appointmentRepository.delete(existingAppointment);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/medical-record")
    public ResponseEntity<?> getMedicalRecordByAppointmentId(@PathVariable Long id) {
        Appointment appointment = appointmentRepository.findAppointmentById(id);
        if (appointment == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No Appointment exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        if (appointment.getMedicalRecord() == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No Medical Record exists for Appointment ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(appointment.getMedicalRecord());
    }
}
