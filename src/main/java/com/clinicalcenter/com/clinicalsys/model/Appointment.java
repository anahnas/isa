package com.clinicalcenter.com.clinicalsys.model;



import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import lombok.Getter;
import lombok.Setter;

import javax.mail.search.SentDateTerm;
import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "appointment")
public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "startTime", unique = false, nullable = false)
    private Date startTime;

    @Column(name = "endTime", unique = false)
    private Date endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppointmentType type;

    @Column(name = "appState", unique = false, nullable = false)
    private AppStateEnum appState;

    /*proveriti jos da li je dobro onetone ili nesto drugo*/
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Appointment() {
    }

    public Appointment(Date startTime, Date endTime, AppointmentType type, Patient patient, Room room, Doctor doctor) {
        this.startTime = startTime;
        this.endTime = endTime;
        this.type = type;
        this.appState=AppStateEnum.REQUESTED;
        this.patient = patient;
        this.doctor = doctor;
        this.room = room;
    }

    public void addRoom(Room room){
        this.room = room;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public AppStateEnum getAppState() {
        return appState;
    }

    public void setAppState(AppStateEnum appState) {
        this.appState = appState;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }
}
