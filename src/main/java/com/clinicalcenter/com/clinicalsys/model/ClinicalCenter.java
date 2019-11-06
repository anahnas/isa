package com.clinicalcenter.com.clinicalsys.model;

import java.util.ArrayList;

public class ClinicalCenter {
    private ArrayList<Clinic> clinics;
    private ClinicalCenter instance = null;
    private ClinicalCenter() {
        this.clinics = new ArrayList<Clinic>();
    }
    public ClinicalCenter getInstance(){
        if(instance == null){
            instance = new ClinicalCenter();
        }
        return instance;
    }

    public ArrayList<Clinic> getClinics() {
        return clinics;
    }
}
