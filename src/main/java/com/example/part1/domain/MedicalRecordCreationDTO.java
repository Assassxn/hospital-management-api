package com.example.part1.domain;

import com.example.part1.validator.AppointmentIdExists;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MedicalRecordCreationDTO {
    @NotBlank(message = "Diagnosis is required")
    String diagnosis;
    @NotBlank(message = "Treatment is required")
    String treatment;
    @NotBlank(message = "Notes are required")
    String notes;
    @AppointmentIdExists
    @NotNull(message = "Appointment ID is required")
    @JsonProperty("appointment_id")
    Long appointmentId;

    public @NotBlank(message = "Diagnosis is required") String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(@NotBlank(message = "Diagnosis is required") String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public @NotBlank(message = "Treatment is required") String getTreatment() {
        return treatment;
    }

    public void setTreatment(@NotBlank(message = "Treatment is required") String treatment) {
        this.treatment = treatment;
    }

    public @NotBlank(message = "Notes are required") String getNotes() {
        return notes;
    }

    public void setNotes(@NotBlank(message = "Notes are required") String notes) {
        this.notes = notes;
    }

    public @NotNull(message = "Appointment ID is required") Long getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(@NotNull(message = "Appointment ID is required") Long appointmentId) {
        this.appointmentId = appointmentId;
    }
}
