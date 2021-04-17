package com.example.lifeline;

public class Hospital {
        public String name, email, phoneno, address;
        public int type, AN, AP, BN, BP, ON, OP, ABN, ABP;

        public Hospital(){

        }
        public Hospital(String name, String email, String phoneno, String address){
            this.name = name;
            this.email = email;
            this.phoneno = phoneno;
            this.address = address;
            type = 2;
            this.AN = 0;
            this.AP = 0;
            this.BN = 0;
            this.BP = 0;
            this.ON = 0;
            this.OP = 0;
            this.ABN = 0;
            this.ABP = 0;
        }
}


