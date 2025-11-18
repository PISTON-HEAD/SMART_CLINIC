package com.example.smart_clinic;

public class GeneralPhysician extends Doctor {
    private double consultationFee;

    public GeneralPhysician(int doctorId, String name, String specialization, int yearsOfExperience,
            double consultationFee) {
        super(doctorId, name, specialization, yearsOfExperience);
        this.consultationFee = consultationFee;
    }

    @Override
    public double calculateCharges() {
        return consultationFee;
    }
}
