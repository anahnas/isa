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

    @ManyToOne(fetch = FetchType.EAGER)
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.EAGER)
    private Drug drug;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @Column(name = "isValidate", unique = false, nullable = true)
    private boolean isValidate;

    public Recipe() {
    }

    public Recipe(Drug drug, Nurse nurse, Patient patient){
        this.drug=drug;
        this.nurse=nurse;
        this.patient=patient;
        this.isValidate = Boolean.FALSE;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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
}
