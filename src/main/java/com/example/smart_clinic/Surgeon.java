package com.example.smart_clinic;

public class Surgeon extends Doctor {
    private double surgeryFee;

    public Surgeon(int doctorId, String name, String specialization, int yearsOfExperience,
            double surgeryFee) {
        super(doctorId, name, specialization, yearsOfExperience);
        this.surgeryFee = surgeryFee;
    }

    @Override
    public double calculateCharges() {
        return surgeryFee;
    }

    public double getSurgeryFee() {
        return surgeryFee;
    }

    public void setSurgeryFee(int surgeryFee) {
        this.surgeryFee = surgeryFee;
    }
}
