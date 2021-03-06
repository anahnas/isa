package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "medicalRecord")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Recipe> recipes;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Drug> drugs_in_user;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Diagnose> diagnoses;

    public MedicalRecord() {
        recipes = new LinkedHashSet<>();
        drugs_in_user = new LinkedHashSet<>();
        diagnoses = new LinkedHashSet<>();
    }

    public void addRecipe(Recipe recipe) {
        this.recipes.add(recipe);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Recipe> getRecipes() {
        return recipes;
    }

    public void setRecipes(Set<Recipe> recipes) {
        this.recipes = recipes;
    }

    public Set<Drug> getDrugs_in_user() {
        return drugs_in_user;
    }

    public void setDrugs_in_user(Set<Drug> drugs_in_user) {
        this.drugs_in_user = drugs_in_user;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
