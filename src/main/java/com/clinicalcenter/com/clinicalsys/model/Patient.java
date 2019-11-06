package com.clinicalcenter.com.clinicalsys.model;

public class Patient extends User{
    private  MedicalHistory medhistory;

    public Patient(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String jmbg) {
        super(email, firstName, lastName, password, address, city, country, phoneNumber, jmbg);
        this.medhistory = new MedicalHistory();
    }



    public Patient() {
        this.medhistory = new MedicalHistory();
    }


}
