package com.clinicalcenter.com.clinicalsys.model;

import java.util.ArrayList;

public class MedicalHistory {
    ArrayList<Drug> drugsInUse;
    ArrayList<Drug> pastDrugs;
    ArrayList<Diagnose> diagnoses;

    public MedicalHistory(){
        this.drugsInUse = new ArrayList<Drug>();
        this.pastDrugs = new ArrayList<Drug>();
        this.diagnoses = new ArrayList<Diagnose>();
    }
}
