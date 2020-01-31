package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff extends User {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Patient> patients;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    private Clinic clinic;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VacationRequest> vacReq;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    public Staff() {
    }

    public Staff(User u,Clinic c){
        super(u);
        clinic = c;
        patients = new HashSet<Patient>();
        vacReq = new HashSet<VacationRequest>();
        appointments = new HashSet<Appointment>();
    }

    public Staff(Staff s){
        this.setEmail(s.getEmail());
        this.setAdminConfirmed(s.getAdminConfirmed());
        this.setActive(s.getActive());
        this.setFirstName(s.getFirstName());
        this.setLastName(s.getLastName());
        this.setPassword(s.getPassword());
        this.setAddress(s.getAddress());
        this.setCity(s.getCity());
        this.setCountry(s.getCountry());
        this.setPhoneNumber(s.getPhoneNumber());
        this.setSsn(s.getSsn());
        this.setRole(s.getRole());
        this.clinic = s.getClinic();
        this.patients = s.getPatients();
        this.vacReq = s.getVacReq();
        this.appointments = s.getAppointments();
    }

    public Set<Patient> getPatients() {
        return patients;
    }

    public void setPatients(Set<Patient> patients) {
        this.patients = patients;
    }

    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Set<VacationRequest> getVacReq() {
        return vacReq;
    }

    public void setVacReq(Set<VacationRequest> vacReq) {
        this.vacReq = vacReq;
    }

    public Set<Appointment> getAppointments() {
        return appointments;
    }

    public void setAppointments(Set<Appointment> appointments) {
        this.appointments = appointments;
    }
}
