package com.example.smart_clinic;

public class Dentist extends Doctor {
    private double dentalProcedureFee;

    public Dentist(int doctorId, String name, String specialization, int yearsOfExperience,
            double dentalProcedureFee) {
        super(doctorId, name, specialization, yearsOfExperience);
        this.dentalProcedureFee = dentalProcedureFee;
    }

    @Override
    public double calculateCharges() {
        return dentalProcedureFee;
    }

    // Getter for dentalProcedureFee
    public double getDentalProcedureFee() {
        return dentalProcedureFee;
    }

    // Setter for dentalProcedureFee
    public void setDentalProcedureFee(double dentalProcedureFee) {
        this.dentalProcedureFee = dentalProcedureFee;
    }
}
