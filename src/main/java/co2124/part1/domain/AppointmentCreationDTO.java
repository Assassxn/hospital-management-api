package co2124.part1.domain;

import jakarta.validation.constraints.NotBlank;
import java.sql.Timestamp;

public class AppointmentCreationDTO {
    @NotBlank(message = "Appointment Date is required")
    Timestamp appointmentDate;
    @NotBlank(message = "Notes are required")
    String notes;
    @NotBlank(message = "Doctor ID is required")
    String doctorId;
    @NotBlank(message = "Patient ID is required")
    String patientId;
}
