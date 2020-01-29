package com.clinicalcenter.com.clinicalsys.model;


import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient extends User{

    /*@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Clinic> clinics;*/

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Appointment> appointments;

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Recipe> recipes;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<AppointReq> reqApp;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Surgery> surgeries;

    @Column(name = "isActivated", unique = false, nullable = true)
    private boolean isActivated;

    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private  MedicalHistory medhistory;*/


   /* public Patient(String email, String firstName, String lastName, String password, String address, String city, String country, String phoneNumber, String jmbg) {
        super(email, firstName, lastName, password, address, city, country, phoneNumber, jmbg);
        this.medhistory = new MedicalHistory();
    }


        */
    public Patient() {

    }

    public Patient(User u){
        super(u);
        this.setActive(Boolean.FALSE);
        this.setAdminConfirmed(Boolean.FALSE);
        this.setRole(RoleEnum.PATIENT);
        this.setFirstLogin(Boolean.TRUE);
    }


}
