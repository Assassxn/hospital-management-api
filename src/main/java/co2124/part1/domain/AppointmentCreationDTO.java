package co2124.part1.domain;

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
    @JsonProperty("doctor_id")
    String doctorId;
    @NotBlank(message = "Patient ID is required")
    @JsonProperty("patient_id")
    String patientId;
}
