package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

enum MEDICAL_STAFF {DOCTOR, NURSE, CLINIC_ADMIN, MAIN_ADMIN}

@Setter
@Getter
@Entity
@Table(name = "medicalStaff")
public class MedicalStaff extends  User{

    private  MEDICAL_STAFF type;

    @Column(name = "rating", unique = false, nullable = false)
    private Float rating;

    public MedicalStaff(MEDICAL_STAFF type) {
        this.type = type;
    }

    public MedicalStaff(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String jmbg, MEDICAL_STAFF type) {
        super(email, firstName, lastName, password, address, city, country, phoneNumber, jmbg);
        this.type = type;
    }
}
