package co2124.part1.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;

import java.sql.Timestamp;

@Entity
public class MedicalRecord {
    @Id
    @GeneratedValue
    Long id;
    @JsonProperty("record_date")
    Timestamp recordDate;
    String diagnosis;
    String treatment;
    String notes;

    @OneToOne
    @JoinColumn(name = "appointment_id", nullable = false)
    private Appointment appointment;

    public MedicalRecord(MedicalRecord medicalRecord) {
        this.id = medicalRecord.id;
        this.recordDate = medicalRecord.recordDate;
        this.diagnosis = medicalRecord.diagnosis;
        this.treatment = medicalRecord.treatment;
        this.notes = medicalRecord.notes;
        this.appointment = medicalRecord.appointment;
    }

    public MedicalRecord() {
    }

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
}
