package com.example.clinic;

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
}
