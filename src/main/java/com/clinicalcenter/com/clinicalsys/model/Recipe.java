package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Optional;

@Setter
@Getter
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeID;

    private Boolean certified;

    public Nurse getNurse() {
        return nurse;
    }

    public void setNurse(Nurse nurse) {
        this.nurse = nurse;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public boolean isValidate() {
        return isValidate;
    }

    public void setValidate(boolean validate) {
        isValidate = validate;
    }

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Drug drug;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;
    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalStaff nurse;*/

    @Column(name = "isValidate", unique = false, nullable = true)
    private boolean isValidate;

    public Recipe() {
    }

}
