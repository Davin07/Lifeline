package com.example.lifeline;

public class DonDeet {
    String address, blood, name, phoneno, type;

    public DonDeet(){

    }

    public DonDeet(String address, String blood, String name, String phoneno, String type) {
        this.address = address;
        this.blood = blood;
        this.name = name;
        this.phoneno = phoneno;
        this.type = type;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBlood() {
        return blood;
    }

    public void setBlood(String blood) {
        this.blood = blood;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
