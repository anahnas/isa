package com.clinicalcenter.com.clinicalsys.model;



import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import lombok.Getter;
import lombok.Setter;

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
    private Long startTime;

    @Column(name = "endTime", unique = false, nullable = false)
    private Long endTime;

    @Column(name = "duration", unique = false, nullable = false)
    private Long duration;

    @Column(name = "dateTime", unique = false, nullable = false)
    private Long dateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppointmentType type;

    @Column(name = "appState", unique = false, nullable = false)
    private AppStateEnum appState;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Room room;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private MedicalHistory medicalHistory;

    @Column(name = "price", unique = false, nullable = false)
    private Float price;

    @Column(name = "discount", unique = false, nullable = false)
    private Float discount;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Appointment() {
    }

   /* public Appointment(AbstractMap<LocalDateTime, LocalDateTime> time, AppointmentType type, Patient patient, ArrayList<MedicalStaff> doctors, Room room, double price) {
        this.time = time;
        this.type = type;
        this.patient = patient;
        this.doctors = doctors;
        this.room = room;
        this.price = price;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AbstractMap<LocalDateTime, LocalDateTime> getTime() {
        return time;
    }

    public void setTime(AbstractMap<LocalDateTime, LocalDateTime> time) {
        this.time = time;
    }

    public AppointmentType getType() {
        return type;
    }

    public void setType(AppointmentType type) {
        this.type = type;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public ArrayList<MedicalStaff> getDoctors() {
        return doctors;
    }

    public void setDoctors(ArrayList<MedicalStaff> doctors) {
        this.doctors = doctors;
    }

    public Room getRoom() {
        return room;
    }

    public void setRoom(Room room) {
        this.room = room;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }*/
}
