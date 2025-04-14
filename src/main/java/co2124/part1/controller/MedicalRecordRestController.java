package co2124.part1.controller;

import co2124.part1.domain.MedicalRecordCreationDTO;
import co2124.part1.entities.Appointment;
import co2124.part1.entities.MedicalRecord;
import co2124.part1.exception.CustomValidationErrorResponse;
import co2124.part1.respos.AppointmentRepository;
import co2124.part1.respos.MedicalRecordRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/medical-records")
public class MedicalRecordRestController {
    @Autowired
    private MedicalRecordRepository medicalRecordRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @PostMapping
    public ResponseEntity<?> addMedicalRecord(@Valid @RequestBody MedicalRecordCreationDTO medicalRecord) {
        Appointment appointment = appointmentRepository.findAppointmentById(medicalRecord.getAppointmentId());
        if (appointment.getMedicalRecord() != null) {
            CustomValidationErrorResponse error = new CustomValidationErrorResponse(
                    LocalDateTime.now(),
                    HttpStatus.BAD_REQUEST.value(),
                    HttpStatus.BAD_REQUEST.getReasonPhrase(),
                    List.of("Medical record already exists for appointment ID: " + medicalRecord.getAppointmentId())
            );

            return new ResponseEntity<>(error, HttpStatus.BAD_REQUEST);
        }

        MedicalRecord newMedicalRecord = new MedicalRecord();
        newMedicalRecord.setDiagnosis(medicalRecord.getDiagnosis());
        newMedicalRecord.setTreatment(medicalRecord.getTreatment());
        newMedicalRecord.setNotes(medicalRecord.getNotes());
        newMedicalRecord.setRecordDate(Timestamp.valueOf(LocalDateTime.now()));
        newMedicalRecord.setAppointment(appointment);

        return ResponseEntity.created(null).body(medicalRecordRepository.save(newMedicalRecord));
    }
}
