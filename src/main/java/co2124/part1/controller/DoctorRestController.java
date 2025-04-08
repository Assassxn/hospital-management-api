package co2124.part1.controller;

import co2124.part1.domain.DoctorCreationDTO;
import co2124.part1.entities.Appointment;
import co2124.part1.entities.Doctor;
import co2124.part1.exception.CustomValidationErrorResponse;
import co2124.part1.respos.DoctorRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/doctors")
public class DoctorRestController {
    @Autowired
    private DoctorRepository doctorRepository;

    @GetMapping
    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    @PostMapping
    public Doctor addDoctor(@Valid @RequestBody DoctorCreationDTO doctor) {
        Doctor newDoctor = new Doctor();
        newDoctor.setName(doctor.getName());
        newDoctor.setSpecialisation(doctor.getSpecialisation());
        newDoctor.setEmail(doctor.getEmail());
        newDoctor.setPhoneNumber(doctor.getPhoneNumber());

        return doctorRepository.save(newDoctor);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getDoctorById(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findDoctorById(id);
        if (doctor == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No doctor exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        return ResponseEntity.ok(doctor);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Long id, @RequestBody Doctor doctor) {
        Doctor existingDoctor = doctorRepository.findDoctorById(id);
        if (existingDoctor == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No doctor exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        existingDoctor.setName(doctor.getName());
        existingDoctor.setEmail(doctor.getEmail());
        existingDoctor.setSpecialisation(doctor.getSpecialisation());
        existingDoctor.setPhoneNumber(doctor.getPhoneNumber());
        Doctor updatedDoctor = doctorRepository.save(existingDoctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Long id) {
        Doctor existingDoctor = doctorRepository.findDoctorById(id);

        if (existingDoctor == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No doctor exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        doctorRepository.delete(existingDoctor);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/appointments")
    public ResponseEntity<?> getAppointmentsByDoctorId(@PathVariable Long id) {
        Doctor doctor = doctorRepository.findDoctorById(id);
        if (doctor == null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.NOT_FOUND.value(),
                    HttpStatus.NOT_FOUND.getReasonPhrase(),
                    List.of("No doctor exists with ID: " + id)
            );
            return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
        }

        List<Appointment> appointments = doctor.getAppointments();
        return ResponseEntity.ok(appointments);
    }
}
