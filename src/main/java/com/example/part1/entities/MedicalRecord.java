package com.example.part1.entities;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    private Long id;
    private Timestamp recordDate;
    private String diagnosis;
    private String treatment;
    private String notes;

    @OneToOne
    @JsonBackReference("appointmentMedicalRecord")
    private Appointment appointment;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("patientMedicalRecord")
    private Patient patient;

    // Helper method to break the relationship
    public void unlinkAppointment() {
        if (this.appointment != null) {
            this.appointment.setMedicalRecord(null); // Remove the forward-reference
            this.appointment = null; // Remove the back-reference
        }
    }

    public MedicalRecord() {}

    public MedicalRecord(MedicalRecord medicalRecord) {
        this.id = medicalRecord.id;
        this.recordDate = medicalRecord.recordDate;
        this.diagnosis = medicalRecord.diagnosis;
        this.treatment = medicalRecord.treatment;
        this.notes = medicalRecord.notes;
        this.appointment = medicalRecord.appointment;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getRecordDate() {
        return recordDate;
    }

    public void setRecordDate(Timestamp recordDate) {
        this.recordDate = recordDate;
    }

    public String getDiagnosis() {
        return diagnosis;
    }

    public void setDiagnosis(String diagnosis) {
        this.diagnosis = diagnosis;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}