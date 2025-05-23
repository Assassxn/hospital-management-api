package com.example.part1.controller;

import com.example.part1.domain.PatientCreationDTO;
import com.example.part1.entities.Appointment;
import com.example.part1.entities.MedicalRecord;
import com.example.part1.entities.Patient;
import com.example.part1.exception.CustomValidationErrorResponse;
import com.example.part1.respos.PatientRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/patients")
public class PatientRestController {
    @Autowired
    private PatientRepository patientRepository;

    @GetMapping
    public ResponseEntity<?> patients() {
        List<Patient> patients = patientRepository.findAll(); // returns [] if no patients exist anyway
        if (patients.isEmpty()) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NO_CONTENT.value(),
                    HttpStatus.NO_CONTENT.getReasonPhrase(),
                    List.of("No patients exist")
            );
            return new ResponseEntity<>(error, HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(patients);
    }

    @PostMapping
    public ResponseEntity<Patient> addPatient(@Valid @RequestBody PatientCreationDTO patientResponse) {
        Patient patient = new Patient();
        patient.setName(patientResponse.getName());
        patient.setEmail(patientResponse.getEmail());
        patient.setPhoneNumber(patientResponse.getPhoneNumber());
        patient.setAddress(patientResponse.getAddress());

        Patient newPatient = patientRepository.save(patient);
        return ResponseEntity.created(null).body(newPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPatientById(@PathVariable Long id) {
        Patient patient = patientRepository.findPatientById(id);
        if (patient == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No patient exists with ID: " + id)
            );

            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(patient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Long id, @Valid @RequestBody PatientCreationDTO patient) {
        Patient existingPatient = patientRepository.findPatientById(id);
        if (existingPatient == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No patient exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        existingPatient.setName(patient.getName());
        existingPatient.setEmail(patient.getEmail());
        existingPatient.setPhoneNumber(patient.getPhoneNumber());
        existingPatient.setAddress(patient.getAddress());
        Patient updatedPatient = patientRepository.save(existingPatient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Long id) {
        Patient existingPatient = patientRepository.findPatientById(id);
        if (existingPatient == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No patient exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        patientRepository.delete(existingPatient);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> getAppointmentsByPatientId(@PathVariable Long id) {
        Patient patient = patientRepository.findPatientById(id);
        if (patient == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No patient exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        List<Appointment> appointments = patient.getAppointments();
        return ResponseEntity.ok(appointments);
    }

    @GetMapping("/{id}/medical-records")
    public ResponseEntity<?> getMedicalRecordsByPatient(@PathVariable Long id) {
        Patient patient = patientRepository.findPatientById(id);
        if (patient == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No patient exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        List<MedicalRecord> records = patient.getMedicalRecords();
        return ResponseEntity.ok(records);
    }
}
