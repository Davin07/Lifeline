package com.example.lifeline;

public class Request {
    String name, address, blood, type, phoneno;

    public Request(String name, String address, String blood, String type, String phoneno) {
        this.name = name;
        this.address = address;
        this.blood = blood;
        this.type = type;
        this.phoneno = phoneno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhoneno() {
        return phoneno;
    }

    public void setPhoneno(String phoneno) {
        this.phoneno = phoneno;
    }
}
