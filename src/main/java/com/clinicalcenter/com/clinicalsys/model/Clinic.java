package com.clinicalcenter.com.clinicalsys.model;

import java.util.AbstractMap;

import java.util.ArrayList;

public class Clinic {
    private String clinicName;
    private String address;
    private String description;
    private ArrayList <Appointment> fastApt;
    private ArrayList <MedicalStaff> staff;
    private ArrayList <Room> rooms;
    private ArrayList <AbstractMap<String, Double>> priceList; //Ne znamo sta ide u cijenovnik

    public Clinic() {
        this.fastApt = new ArrayList<Appointment>();
        this.staff = new ArrayList<MedicalStaff>();
        this.rooms = new ArrayList<Room>();
    }

    public Clinic(String clinicName, String address, String description) {
        this.clinicName = clinicName;
        this.address = address;
        this.description = description;
        this.fastApt = new ArrayList<Appointment>();
        this.staff = new ArrayList<MedicalStaff>();
        this.rooms = new ArrayList<Room>();
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

    public ArrayList<MedicalStaff> getStaff() {
        return staff;
    }

    public void setStaff(ArrayList<MedicalStaff> staff) {
        this.staff = staff;
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }

    public void setRooms(ArrayList<Room> rooms) {
        this.rooms = rooms;
    }

    public ArrayList<AbstractMap<String, Double>> getPriceList() {
        return priceList;
    }

    public void setPriceList(ArrayList<AbstractMap<String, Double>> priceList) {
        this.priceList = priceList;
    }
}
