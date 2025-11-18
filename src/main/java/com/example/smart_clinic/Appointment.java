package com.example.clinic;

import java.time.LocalDateTime;

public class Appointment {
    private int appointmentId;
    private LocalDateTime date;
    private String type;
    private String status;
    private Patient patient;
    private Doctor doctor;

    public Appointment(int appointmentId, LocalDateTime date,
            String type,
            Patient patient, Doctor doctor) {
        this.appointmentId = appointmentId;
        this.date = date;
        this.type = type;
        this.status = "Scheduled";
        this.patient = patient;
        this.doctor = doctor;
    }

    // Getters and Setters
    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public String showAppointment() {
        return String.format(
                "Appointment %d for %s with %s is %s",
                this.getAppointmentId(),
                patient.getName(),
                doctor.getName(),
                this.getStatus());

    }

    public double calculateBill() {
        return doctor.calculateCharges();
    }
}
