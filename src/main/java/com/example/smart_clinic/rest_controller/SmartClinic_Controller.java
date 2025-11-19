package com.example.smart_clinic.rest_controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.smart_clinic.Appointment;
import com.example.smart_clinic.Doctor;
import com.example.smart_clinic.Patient;
import com.example.smart_clinic.service_layer.ClinicService;

@RestController
@RequestMapping("/clinic")
public class SmartClinic_Controller {

 private ClinicService clinicService;

 @Autowired
 public SmartClinic_Controller(ClinicService clinicService) {
  this.clinicService = clinicService;
 }

 // ================= Doctor Endpoints =================

 @PostMapping("/doctor/register")
 public Doctor registerDoctor(@RequestBody Doctor doctor) {
  return clinicService.registerDoctor(doctor);
 }

 @GetMapping("/doctors")
 public List<Doctor> getAllDoctors() {
  return clinicService.getAllDoctors();
 }

 @GetMapping("/doctor/top-earning")
 public Doctor getTopEarningDoctor() {
  return clinicService.getTopEarningDoctor();
 }

 @PostMapping("/patient/register")
 public Patient registerPatient(@RequestBody Patient patient) {
  return clinicService.registerPatient(patient);
 }

 @GetMapping("/patients")
 public List<Patient> getAllPatients() {
  return clinicService.getAllPatients();
 }

 @GetMapping("/patients/frequent")
 public List<Patient> getFrequentPatients(@RequestParam int minVisits) {
  return clinicService.getFrequentPatients(minVisits);
 }

 @PostMapping("/appointment/book")
 public Appointment bookAppointment(
   @RequestParam int doctorId,
   @RequestParam int patientId,
   @RequestParam String date, // you might parse this to LocalDate in service
   @RequestParam String type) {
  return clinicService.bookAppointment(doctorId, patientId, date, type);
 }

 @PostMapping("/appointment/complete/{appointmentId}")
 public Appointment completeAppointment(@PathVariable int appointmentId) {
  return clinicService.completeAppointment(appointmentId);
 }

 @GetMapping("/doctor/{doctorId}/appointments")
 public List<Appointment> getAppointmentsByDoctor(@PathVariable int doctorId) {
  return clinicService.getAppointmentsByDoctor(doctorId);
 }

 @GetMapping("/appointments/grouped/type")
 public Map<String, List<Appointment>> getAppointmentsGroupedByType() {
  return clinicService.getAppointmentsGroupedByType();
 }
}
