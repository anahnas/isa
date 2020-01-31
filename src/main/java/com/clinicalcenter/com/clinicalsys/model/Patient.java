package com.clinicalcenter.com.clinicalsys.model;


import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Setter
@Getter
@Entity
@Table(name = "patient")
public class Patient extends User{

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalRecord medicalRecord;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Surgery> surgeries;

    public Patient() {

    }

    public Patient(User u){
        super(u);
        this.setActive(Boolean.FALSE);
        this.setAdminConfirmed(Boolean.FALSE);
        this.setRole(RoleEnum.PATIENT);
        this.setFirstLogin(Boolean.TRUE);
        this.medicalRecord = new MedicalRecord();
        this.appointments = new HashSet<Appointment>();
        this.surgeries = new HashSet<Surgery>();
    }

    public MedicalRecord getMedicalRecord() {
        return medicalRecord;
    }

    public void setMedicalRecord(MedicalRecord medicalRecord) {
        this.medicalRecord = medicalRecord;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }

    public Set<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Set<Surgery> surgeries) {
        this.surgeries = surgeries;
    }
}
