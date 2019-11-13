package com.clinicalcenter.com.clinicalsys.model;

import java.util.ArrayList;

public class MedicalHistory {

    private Long id;
    private ArrayList<Drug> drugsInUse;
    private ArrayList<Drug> pastDrugs;
    private ArrayList<Diagnose> diagnoses;

    public MedicalHistory(){
        this.drugsInUse = new ArrayList<Drug>();
        this.pastDrugs = new ArrayList<Drug>();
        this.diagnoses = new ArrayList<Diagnose>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public ArrayList<Drug> getDrugsInUse() {
        return drugsInUse;
    }

    public void setDrugsInUse(ArrayList<Drug> drugsInUse) {
        this.drugsInUse = drugsInUse;
    }

    public ArrayList<Drug> getPastDrugs() {
        return pastDrugs;
    }

    public void setPastDrugs(ArrayList<Drug> pastDrugs) {
        this.pastDrugs = pastDrugs;
    }

    public ArrayList<Diagnose> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(ArrayList<Diagnose> diagnoses) {
        this.diagnoses = diagnoses;
    }
}
