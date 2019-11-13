package com.clinicalcenter.com.clinicalsys.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient extends User{
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  MedicalHistory medhistory;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments;

    public Patient(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String jmbg) {
        super(email, firstName, lastName, password, address, city, country, phoneNumber, jmbg);
        this.medhistory = new MedicalHistory();
    }



    public Patient() {
        this.medhistory = new MedicalHistory();
    }


}
