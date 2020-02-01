package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "medicalRecord")
public class MedicalRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Drug> drugs_in_user;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Diagnose> diagnoses;
    public MedicalRecord(){

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
