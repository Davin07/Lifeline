package com.example.lifeline;

public class HospDeet {
    String BloodType, Name, Address, PhoneNo;
    int positive, negative;

    public HospDeet(){

    }

    public HospDeet(String bloodType, String name, String address, String phoneNo, int positive, int negative) {
        BloodType = bloodType;
        Name = name;
        Address = address;
        PhoneNo = phoneNo;
        this.positive = positive;
        this.negative = negative;
    }

    public String getBloodType() {
        return BloodType;
    }

    public void setBloodType(String bloodType) {
        BloodType = bloodType;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNo() {
        return PhoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        PhoneNo = phoneNo;
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
