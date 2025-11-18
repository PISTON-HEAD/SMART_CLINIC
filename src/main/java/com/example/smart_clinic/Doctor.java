package com.example.clinic;

public abstract class Doctor {
    public int id;
    public String name;
    public String specialty;
    public int experienceYears;

    public Doctor(int id, String name, String specialty, int experienceYears) {
        this.id = id;
        this.name = name;
        this.specialty = specialty;
        this.experienceYears = experienceYears;
    }

    public String getName() {
        return name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public int getExperienceYears() {
        return experienceYears;
    }

    public int getId() {
        return id;
    }

    public abstract double calculateCharges();
}
