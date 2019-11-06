package com.clinicalcenter.com.clinicalsys.model;
enum MEDICAL_STAFF {DOCTOR, NURSE, CLINIC_ADMIN, MAIN_ADMIN}
public class MedicalStaff extends  User{
    private  MEDICAL_STAFF type;

    public MedicalStaff(MEDICAL_STAFF type) {
        this.type = type;
    }

    public MedicalStaff(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String jmbg, MEDICAL_STAFF type) {
        super(email, firstName, lastName, password, address, city, country, phoneNumber, jmbg);
        this.type = type;
    }
}
