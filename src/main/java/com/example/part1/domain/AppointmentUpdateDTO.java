package com.example.part1.domain;

import com.example.part1.enums.AppointmentStatus;
import com.example.part1.validator.DoctorIdExists;
import com.example.part1.validator.PatientIdExists;
import com.example.part1.validator.ValidEnum;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.sql.Timestamp;

public class AppointmentUpdateDTO {
    @NotNull(message = "Appointment Date is required")
    @JsonProperty("appointment_date")
    private Timestamp appointmentDate;

    @NotBlank(message = "Notes are required")
    private String notes;

    @NotNull(message = "Doctor ID is required")
    @DoctorIdExists
    @JsonProperty("doctor_id")
    private Long doctorId;

    @NotNull(message = "Patient ID is required")
    @PatientIdExists
    @JsonProperty("patient_id")
    private Long patientId;

    @NotBlank(message = "Status is required")
    @ValidEnum(enumClass = AppointmentStatus.class, message = "Status must be one of the following: SCHEDULED, COMPLETED, CANCELLED")
    private String status;

    public @NotNull(message = "Appointment Date is required") Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(@NotNull(message = "Appointment Date is required") Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public @NotBlank(message = "Notes are required") String getNotes() {
        return notes;
    }

    public void setNotes(@NotBlank(message = "Notes are required") String notes) {
        this.notes = notes;
    }

    public @NotNull(message = "Doctor ID is required") Long getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(@NotNull(message = "Doctor ID is required") Long doctorId) {
        this.doctorId = doctorId;
    }

    public @NotNull(message = "Patient ID is required") Long getPatientId() {
        return patientId;
    }

    public void setPatientId(@NotNull(message = "Patient ID is required") Long patientId) {
        this.patientId = patientId;
    }

    public @NotBlank(message = "Status is required") String getStatus() {
        return status;
    }

    public void setStatus(@NotBlank(message = "Status is required") String status) {
        this.status = status;
    }
}
