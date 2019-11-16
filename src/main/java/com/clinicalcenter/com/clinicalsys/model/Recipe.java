package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "recipe")
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String recipeID;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Nurse nurse;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Drug drug;

    /*@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalStaff nurse;*/

    @Column(name = "isValidate", unique = false, nullable = true)
    private boolean isValidate;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalHistory medicalHistory;

    public Recipe() {
    }

    /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Recipe(String recipeID) {
        this.recipeID = recipeID;
    }

    public String getRecipeID() {
        return recipeID;
    }

    public void setRecipeID(String recipeID) {
        this.recipeID = recipeID;
    }

    public MedicalStaff getDoctor() {
        return doctor;
    }

    public void setDoctor(MedicalStaff doctor) {
        this.doctor = doctor;
    }

    public MedicalStaff getNurse() {
        return nurse;
    }

    public void setNurse(MedicalStaff nurse) {
        this.nurse = nurse;
    }*/
}
