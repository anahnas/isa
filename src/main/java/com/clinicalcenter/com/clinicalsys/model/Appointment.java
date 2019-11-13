package com.clinicalcenter.com.clinicalsys.model;



import java.time.LocalDateTime;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private Long id;
    private AbstractMap<LocalDateTime,LocalDateTime> time;
    private AppointmentType type;
    private Patient patient;
    private ArrayList<MedicalStaff> doctors;
    private Room room;
    private double price;

    public Appointment() {
    }

    public Appointment(AbstractMap<LocalDateTime, LocalDateTime> time, AppointmentType type, Patient patient, ArrayList<MedicalStaff> doctors, Room room, double price) {
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
    }
}
