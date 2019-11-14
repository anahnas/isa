package com.clinicalcenter.com.clinicalsys.model;

import javax.persistence.*;
import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.Set;

@Entity
@Table(name = "clinic")
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinicName", nullable = false)
    private String clinicName;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ArrayList <Appointment> fastApt;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set <MedicalStaff> staff; //doctor or nurse

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ArrayList <AbstractMap<String, Double>> priceList; //Ne znamo sta ide u cijenovnik

    public Clinic() {
        super();
        this.fastApt = new ArrayList<Appointment>();
        //this.staff = new Set<MedicalStaff>();
        //this.rooms = new Set<Room>();
    }

    public Clinic(String clinicName, String address, String description) {
        this.clinicName = clinicName;
        this.address = address;
        this.description = description;
        this.fastApt = new ArrayList<Appointment>();
        //this.staff = new Set<MedicalStaff>();
        //this.rooms = new Set<Room>();
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Appointment> getFastApt() {
        return fastApt;
    }

    public void setFastApt(ArrayList<Appointment> freeApt) {
        this.fastApt = freeApt;
    }

    public Set<MedicalStaff> getStaff() {
        return staff;
    }

    public void setStaff(Set<MedicalStaff> staff) {
        this.staff = staff;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<AbstractMap<String, Double>> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList<AbstractMap<String, Double>> priceList) {
        this.priceList = priceList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
