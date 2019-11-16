package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "medicalHistory")
public class MedicalHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Drug> drugsInUse;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Drug> pastDrugs;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Diagnose> diagnoses;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Appointment appointment;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Recipe> recipes;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;


    public MedicalHistory(){
    }

   /* public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Set<Drug> getDrugsInUse() {
        return drugsInUse;
    }

    public void setDrugsInUse(Set<Drug> drugsInUse) {
        this.drugsInUse = drugsInUse;
    }

    public Set<Drug> getPastDrugs() {
        return pastDrugs;
    }

    public void setPastDrugs(Set<Drug> pastDrugs) {
        this.pastDrugs = pastDrugs;
    }

    public Set<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(Set<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }*/
}
