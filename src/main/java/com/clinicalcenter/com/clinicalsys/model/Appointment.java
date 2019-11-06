package com.clinicalcenter.com.clinicalsys.model;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;

public class Appointment {
    private Pair<LocalDateTime,LocalDateTime> time;
    private TYPE type;
    private Patient patient;
    private ArrayList<MedicalStaff> doctors;
    private Room room;
    private double price;

    public Appointment() {
    }

    public Appointment(Pair<LocalDateTime, LocalDateTime> time, TYPE type, Patient patient, ArrayList<MedicalStaff> doctors, Room room, double price) {
        this.time = time;
        this.type = type;
        this.patient = patient;
        this.doctors = doctors;
        this.room = room;
        this.price = price;
    }
}
