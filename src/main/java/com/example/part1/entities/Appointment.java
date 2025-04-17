package com.example.part1.entities;

import com.example.part1.enums.AppointmentStatus;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.sql.Timestamp;
@Entity
public class Appointment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Timestamp appointmentDate;
    private AppointmentStatus status = AppointmentStatus.SCHEDULED;
    private String notes;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("doctorAppointments")
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    @JsonBackReference("patientAppointments")
    private Patient patient;

    @OneToOne(mappedBy = "appointment", cascade = {CascadeType.PERSIST, CascadeType.MERGE}, orphanRemoval = true)
    private MedicalRecord medicalRecord;

    // Automatically unlink the MedicalRecord before deletion
    @PreRemove
    private void unlinkMedicalRecord() {
        if (this.medicalRecord != null) {
            this.medicalRecord.unlinkAppointment();
        }
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(Timestamp appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public AppointmentStatus getStatus() {
        return status;
    }

    public void setStatus(AppointmentStatus status) {
        this.status = status;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }
}