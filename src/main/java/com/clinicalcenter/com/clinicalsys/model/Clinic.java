package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.Set;

import static javax.persistence.InheritanceType.JOINED;

@Setter
@Getter
@Entity
@Table(name = "clinic")
@Inheritance(strategy = JOINED)
public class Clinic {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinicName", nullable = false)
    private String clinicName;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "rating", unique = false, nullable = true)
    private Float rating;

//    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private ClinicReport clinicReport;
//
//    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private PriceList priceList;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private  Set<Appointment> fastApt;
//
//   /* @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set <MedicalStaff> staff; //doctor or nurse ovaj je i bio zakom jer ne koristimo vise med staff*/
//
//   //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//   private Set<Doctor> doctors;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Nurse> nurses;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<ClinicAdmin> clinicAdmins;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<Room> rooms;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<VacationRequest> vacReq;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<AppointReq> appReqs;
//
//    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private ClinicalCenter clinicCenter;
//
//    //@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
//    private Set<SurgeryReq> surReq;

    public Clinic() {}

    public Clinic(String clinicName, String address, String description, Float rating) {
        this.clinicName = clinicName;
        this.address = address;
        this.description = description;
        this.rating = rating;
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

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    /*  public Clinic(String clinicName, String address, String description) {
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
    }*/
}
