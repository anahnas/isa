package com.clinicalcenter.com.clinicalsys.model;

public class Recipe {

    private Long id;
    private String recipeID;
    private MedicalStaff doctor;
    private MedicalStaff nurse;

    public Recipe() {
    }

    public Long getId() {
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
    }
}
