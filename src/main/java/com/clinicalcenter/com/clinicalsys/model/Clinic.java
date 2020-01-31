package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.AbstractMap;

import java.util.ArrayList;
import java.util.HashSet;
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

    @Column(name = "clinicName", unique = true, nullable = false)
    private String clinicName;

    @Column(name = "address", unique = false, nullable = false)
    private String address;

    @Column(name = "city", unique = false, nullable = false)
    private String city;

    @Column(name = "country", unique = false, nullable = false)
    private String country;

    @Column(name = "description", unique = false, nullable = true)
    private String description;

    @Column(name = "rating", unique = false, nullable = true)
    private Float rating;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Room> rooms;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppointmentType> appointmentTypes;

    public Clinic() {}

    public Clinic(String clinicName, String address, String description, Float rating, String city, String country) {
        this.clinicName = clinicName;
        this.address = address;
        this.description = description;
        this.rating = rating;
        this.city = city;
        this.country = country;
        this.rooms = new HashSet<Room>();
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

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public Set<Room> getRooms() {
        return rooms;
    }

    public void setRooms(Set<Room> rooms) {
        this.rooms = rooms;
    }

    public Set<AppointmentType> getAppointmentTypes() {
        return appointmentTypes;
    }

    public void setAppointmentTypes(Set<AppointmentType> appointmentTypes) {
        this.appointmentTypes = appointmentTypes;
    }
}
