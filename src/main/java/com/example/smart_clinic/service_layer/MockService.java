package com.example.smart_clinic.service_layer;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.smart_clinic.Appointment;
import com.example.smart_clinic.Doctor;
import com.example.smart_clinic.Patient;

@Service
public class MockService implements ClinicService {

   ArrayList<Doctor> doctors;
   ArrayList<Patient> patients;
   ArrayList<Appointment> appointments;

   MockService() {
      this.doctors = new ArrayList<>();
      this.patients = new ArrayList<>();
      this.appointments = new ArrayList<>();
   }

   @Override
   public Doctor registerDoctor(Doctor doctor) {
      Doctor verifiedDoctor = verifyDoctor(doctor.getId());
      if (verifiedDoctor == null) {
         doctors.add(doctor);
         return doctor;
      } else {
         return null;
      }
   }

   private Doctor verifyDoctor(int id) {
      return doctors.stream().filter(d -> d.getId() == id).findAny().orElse(null);
   }

   @Override
   public Patient registerPatient(Patient patient) {
      Patient verifiedPatient = verifyPatient(patient.getPatientId());
      if (verifiedPatient == null) {
         patients.add(patient);
         return patient;
      }
      return verifiedPatient;
   }

   private Patient verifyPatient(int id) {
      return patients.stream()
            .filter(p -> p.getPatientId() == id)
            .findFirst()
            .orElse(null);
   }

   @Override
   public Appointment bookAppointment(int doctorId, int patientId, String date, String type) {
      Doctor verifiedDoctor = verifyDoctor(doctorId);
      Patient verifiedPatient = verifyPatient(patientId);
      if (verifiedDoctor == null || verifiedPatient == null) {
         return null;
      }

      generateAppointmentId();
      Appointment appointment = new Appointment(generateAppointmentId(), date, type, verifiedPatient, verifiedDoctor);

      appointments.add(appointment);

      return appointment;

   }

   private int appointmentCounter = 1000;

   private int generateAppointmentId() {
      return (++appointmentCounter);
   }

   @Override
   public Appointment completeAppointment(int appointmentId) {

      Appointment verifiedAppointment = verifyAppointment(appointmentId);
      if (verifiedAppointment == null) {
         return verifiedAppointment;
      } else {
         verifiedAppointment.setStatus("Completed");
      }
      return verifiedAppointment;
   }

   private Appointment verifyAppointment(int appointmentId) {
      return appointments.stream().filter(i -> i.getAppointmentId() == appointmentId).findFirst().orElse(null);
   }

   @Override
   public ArrayList<Doctor> getAllDoctors() {
      return doctors;
   }

   @Override
   public ArrayList<Patient> getAllPatients() {
      patients.stream().sorted(Comparator.comparing(Patient::getName));
      return patients;
   }

   @Override
   public ArrayList<Appointment> getAppointmentsByDoctor(int doctorId) {

      return appointments.stream().filter(i -> i.getDoctor().getId() == doctorId)
            .collect(Collectors.toCollection(ArrayList::new));

   }

   @Override
   public ArrayList<Appointment> getCompletedAppointmentsByDoctor(int doctorId) {
      return appointments.stream().filter(i -> i.getDoctor().getId() == doctorId && i.getStatus().equals("Completed"))
            .collect(Collectors.toCollection(ArrayList::new));
   }

   @Override
   public List<Appointment> getAppointmentsByDoctorSortedByDate(int doctorId) {
      return appointments.stream().filter(i -> i.getDoctor().getId() == doctorId)
            .sorted(Comparator.comparing(Appointment::getDate)).collect(Collectors.toList());
   }

   @Override
   public double getAverageBillForDoctor(int doctorId) {
      double avg = appointments.stream().filter(i -> i.getDoctor().getId() == doctorId)
            .mapToDouble(Appointment::calculateBill).average().orElse(0.0);
      return avg;
   }

   @Override
   public Doctor getTopEarningDoctor() {

      return appointments.stream()
            .collect(Collectors.groupingBy(
                  Appointment::getDoctor,
                  Collectors.summingDouble(Appointment::calculateBill)))
            .entrySet()
            .stream()
            .max(Map.Entry.comparingByValue())
            .map(Map.Entry::getKey)
            .orElse(null);
   }

   @Override
   public Map<String, List<Appointment>> getAppointmentsGroupedByType() {
      return appointments.stream().collect(Collectors.groupingBy(Appointment::getType));
   }

   @Override
   public ArrayList<Patient> getFrequentPatients(int minVisits) {
      return appointments.stream().collect(Collectors.groupingBy(Appointment::getPatient)).entrySet().stream()
            .filter(i -> i.getValue().size() >= minVisits).map(Map.Entry::getKey)
            .collect(Collectors.toCollection(ArrayList::new));
   }

}
