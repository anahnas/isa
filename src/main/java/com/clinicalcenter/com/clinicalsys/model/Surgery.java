package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "surgery")
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room Room;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Doctor> doctors;

    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @Column(name = "startTime", nullable = false)
    private Date startTime;

    @Column(name = "endTime", nullable = false)
    private Date endTime;

    @Column(name="approved")
    private Boolean approved;

    public Surgery(Date startTime,Date endTime,Patient patient, Doctor doctor, Room room, Boolean activated) {
        Room = room;
        this.doctors = new HashSet<>();
        doctors.add(doctor);
        this.patient = patient;
        this.startTime = startTime;
        this.endTime = endTime;
        this.approved = activated;
    }

    public Surgery() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public com.clinicalcenter.com.clinicalsys.model.Room getRoom() {
        return Room;
    }

    public void setRoom(com.clinicalcenter.com.clinicalsys.model.Room room) {
        Room = room;
    }

    public Set<Doctor> getDoctors() {
        return doctors;
    }

    public void setDoctors(Set<Doctor> doctors) {
        this.doctors = doctors;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public Boolean getApproved() {
        return approved;
    }

    public void setApproved(Boolean approved) {
        this.approved = approved;
    }
}
