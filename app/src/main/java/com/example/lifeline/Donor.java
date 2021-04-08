package com.example.lifeline;

public class Donor{
    public String name, email, dob, phoneno, weight, height, bloodtype;
    public int type;

    public Donor(){

    }

    public Donor(String name, String email, String dob, String phoneno, String weight, String height, String bloodtype){
        this.name = name;
        this.email = email;
        this.dob = dob;
        this.phoneno = phoneno;
        this.weight = weight;
        this.height = height;
        this.bloodtype = bloodtype;
        type = 1;
    }

}
