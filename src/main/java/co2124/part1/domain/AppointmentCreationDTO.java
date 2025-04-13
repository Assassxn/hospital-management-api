package co2124.part1.domain;

import co2124.part1.validator.DoctorIdExists;
import co2124.part1.validator.PatientIdExists;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class AppointmentCreationDTO {
    @NotBlank(message = "Appointment Date is required")
    @JsonProperty("appointment_date")
    Timestamp appointmentDate;
    @NotBlank(message = "Notes are required")
    String notes;
    @NotBlank(message = "Doctor ID is required")
    @DoctorIdExists
    @JsonProperty("doctor_id")
    String doctorId;
    @NotBlank(message = "Patient ID is required")
    @JsonProperty("patient_id")
    @PatientIdExists
    String patientId;
}
