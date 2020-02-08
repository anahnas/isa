package com.clinicalcenter.com.clinicalsys.model;


import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient extends User {
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private MedicalRecord medicalRecord;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> future_appointments;

    @OneToMany(fetch = FetchType.EAGER)
    @com.fasterxml.jackson.annotation.JsonIgnore
    private Set<Surgery> surgeries;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Rating> ratings;

    public Patient() {

    }

    public Patient(User u) {
        super(u);
        this.setActive(Boolean.FALSE);
        this.setAdminConfirmed(Boolean.FALSE);
        this.setRole(RoleEnum.PATIENT);
        this.setFirstLogin(Boolean.TRUE);
        this.medicalRecord = new MedicalRecord();
        this.future_appointments = new HashSet<Appointment>();
        this.surgeries = new HashSet<Surgery>();
        this.ratings = new HashSet<Rating>();
    }

    public void rate(Rating new_rating){
         Long clinicId=new_rating.getClinicId();
        Long doctorId = new_rating.getDoctorId();
        for(Rating rating: ratings){
            if(rating.getClinicId()!=null&&clinicId!=null&&rating.getClinicId().compareTo(clinicId)==0||
                    (rating.getDoctorId()!=null&&doctorId!=null&&rating.getDoctorId().compareTo(doctorId)==0)){
                ratings.remove(rating);
                break;
            }
        }
        ratings.add(new_rating);
    }

    public Set<Rating> getRatings() {
        return ratings;
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Set<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Set<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    public Set<Appointment> getFuture_appointments() {
        return future_appointments;
    }

    public void setFuture_appointments(Set<Appointment> future_appointments) {
        this.future_appointments = future_appointments;
    }
}
