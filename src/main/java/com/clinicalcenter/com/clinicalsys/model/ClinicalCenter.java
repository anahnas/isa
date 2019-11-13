package com.clinicalcenter.com.clinicalsys.model;

import java.util.ArrayList;
import java.util.HashMap;

public class ClinicalCenter {
    private Long id;
    private String name;
    private ArrayList<Clinic> clinics;
    private HashMap <String, Drug> drugHashMap;
    private HashMap <String, Diagnose> diagnoseHashMap;
    private ArrayList<User> allUsers;

    public ArrayList<Patient> getRequests() {
        return requests;
    }

    private ArrayList<Patient> requests;
    private ClinicalCenter instance = null;

    private ClinicalCenter() {
        this.clinics = new ArrayList<Clinic>();
        this.drugHashMap = new HashMap<String, Drug>();
        this.diagnoseHashMap = new HashMap<String, Diagnose>();
        this.allUsers = new ArrayList<User>();
        this.requests = new ArrayList<Patient>();
    }

    public ClinicalCenter(String name) {
        this.name = name;
        this.clinics = new ArrayList<Clinic>();
        this.drugHashMap = new HashMap<String, Drug>();
        this.diagnoseHashMap = new HashMap<String, Diagnose>();
        this.allUsers = new ArrayList<User>();
        this.requests = new ArrayList<Patient>();
    }

    public ClinicalCenter getInstance(){
        if(instance == null){
            instance = new ClinicalCenter();
        }
        return instance;
    }

    public HashMap<String, Drug> getDrugHashMap() {
        return drugHashMap;
    }

    public HashMap<String, Diagnose> getDiagnoseHashMap() {
        return diagnoseHashMap;
    }

    public ArrayList<User> getAllUsers() {
        return allUsers;
    }

    public ArrayList<Clinic> getClinics() {
        return clinics;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setClinics(ArrayList<Clinic> clinics) {
        this.clinics = clinics;
    }

    public void setDrugHashMap(HashMap<String, Drug> drugHashMap) {
        this.drugHashMap = drugHashMap;
    }

    public void setDiagnoseHashMap(HashMap<String, Diagnose> diagnoseHashMap) {
        this.diagnoseHashMap = diagnoseHashMap;
    }

    public void setAllUsers(ArrayList<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void setRequests(ArrayList<Patient> requests) {
        this.requests = requests;
    }

    public void setInstance(com.clinicalcenter.com.clinicalsys.model.ClinicalCenter instance) {
        this.instance = instance;
    }
}
