package com.example.lifeline;

public class DonDeet {
    private String Address, Blood, Btype, Name, Phoneno;

    public DonDeet(){

    }

    public DonDeet(String address, String blood, String btype, String name, String phoneno) {
        Address = address;
        Blood = blood;
        Btype = btype;
        Name = name;
        Phoneno = phoneno;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getBlood() {
        return Blood;
    }

    public void setBlood(String blood) {
        Blood = blood;
    }

    public String getBtype() {
        return Btype;
    }

    public void setBtype(String btype) {
        Btype = btype;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPhoneno() {
        return Phoneno;
    }

    public void setPhoneno(String phoneno) {
        Phoneno = phoneno;
    }
}
