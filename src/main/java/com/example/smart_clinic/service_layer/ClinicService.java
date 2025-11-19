package com.example.smart_clinic.service_layer;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.example.smart_clinic.Appointment;
import com.example.smart_clinic.Doctor;
import com.example.smart_clinic.Patient;

public interface ClinicService {
    Doctor registerDoctor(Doctor doctor);

    Patient registerPatient(Patient patient);

    Appointment bookAppointment(int doctorId, int patientId, String date, String type);

    Appointment completeAppointment(int appointmentId);

    ArrayList<Doctor> getAllDoctors();

    ArrayList<Patient> getAllPatients();

    ArrayList<Appointment> getAppointmentsByDoctor(int doctorId);

    ArrayList<Appointment> getCompletedAppointmentsByDoctor(int doctorId);

    List<Appointment> getAppointmentsByDoctorSortedByDate(int doctorId);

    double getAverageBillForDoctor(int doctorId);

    Doctor getTopEarningDoctor();

    Map<String, List<Appointment>> getAppointmentsGroupedByType();

    ArrayList<Patient> getFrequentPatients(int minVisits);
}
