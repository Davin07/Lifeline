package com.example.lifeline;

public class HospDeet {
    private String BloodType;
    private int positive, negative;

    public HospDeet(String bloodType, int positive, int negative) {
        BloodType = bloodType;
        this.positive = positive;
        this.negative = negative;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public int getPositive() {
        return positive;
    }

    public void setPositive(int positive) {
        this.positive = positive;
    }

    public int getNegative() {
        return negative;
    }

    public void setNegative(int negative) {
        this.negative = negative;
    }
}
