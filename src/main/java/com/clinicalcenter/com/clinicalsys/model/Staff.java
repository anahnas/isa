package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff extends User {

    @ManyToOne(fetch = FetchType.EAGER)
    private Clinic clinic;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<VacationRequest> vacReq;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Appointment> appointments;

    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Surgery> surgeries;

    public Staff() {
    }

    public Staff(User u, Clinic c) {
        super(u);
        clinic = c;
        vacReq = new HashSet<VacationRequest>();
        appointments = new HashSet<Appointment>();
    }

    public Staff(Staff s) {
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
        this.vacReq = s.getVacReq();
        this.appointments = s.getAppointments();
    }

    public void addAppointment(Appointment apt) {
        this.appointments.add(apt);
    }

    //region Getters/Setters
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

    public Set<Surgery> getSurgeries() {
        return surgeries;
    }

    public void setSurgeries(Set<Surgery> surgeries) {
        this.surgeries = surgeries;
    }

    //endregion
}
