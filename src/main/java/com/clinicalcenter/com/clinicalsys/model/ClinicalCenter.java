package com.clinicalcenter.com.clinicalsys.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "clinicalCenter")
public class ClinicalCenter {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Clinic> clinics;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HashMap <String, Drug> drugHashMap;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private HashMap <String, Diagnose> diagnoseHashMap;

    private Set<User> allUsers;

    public ArrayList<Patient> getRequests() {
        return requests;
    }

    private ArrayList<Patient> requests;
    private ClinicalCenter instance = null;

    private ClinicalCenter() {
        super();
        //this.clinics = new Set<Clinic>();
        this.drugHashMap = new HashMap<String, Drug>();
        this.diagnoseHashMap = new HashMap<String, Diagnose>();
        //this.allUsers = new Set<User>();
        this.requests = new ArrayList<Patient>();
    }

    public ClinicalCenter(String name) {
        super();
        this.name = name;
        //this.clinics = new Set<Clinic>();
        this.drugHashMap = new HashMap<String, Drug>();
        this.diagnoseHashMap = new HashMap<String, Diagnose>();
       // this.allUsers = new Set<User>();
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

    public Set<User> getAllUsers() {
        return allUsers;
    }

    public Set<Clinic> getClinics() {
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

    public void setClinics(Set<Clinic> clinics) {
        this.clinics = clinics;
    }

    public void setDrugHashMap(HashMap<String, Drug> drugHashMap) {
        this.drugHashMap = drugHashMap;
    }

    public void setDiagnoseHashMap(HashMap<String, Diagnose> diagnoseHashMap) {
        this.diagnoseHashMap = diagnoseHashMap;
    }

    public void setAllUsers(Set<User> allUsers) {
        this.allUsers = allUsers;
    }

    public void setRequests(ArrayList<Patient> requests) {
        this.requests = requests;
    }

    public void setInstance(com.clinicalcenter.com.clinicalsys.model.ClinicalCenter instance) {
        this.instance = instance;
    }
}
