package com.clinicalcenter.com.clinicalsys.model;

import javafx.util.Pair;

import java.time.LocalDateTime;
import java.util.ArrayList;
enum TYPE {SURGERY, EXAMINATION}
public class Room {
    private String id;
    private ArrayList<Pair<LocalDateTime,LocalDateTime>> reservedApt;
    private TYPE type;

    public Room() {
        reservedApt = new ArrayList<Pair<LocalDateTime, LocalDateTime>>();
    }

    public Room(String id, TYPE type) {
        this.id = id;
        this.type = type;
        reservedApt = new ArrayList<Pair<LocalDateTime, LocalDateTime>>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<Pair<LocalDateTime, LocalDateTime>> getReservedApt() {
        return reservedApt;
    }

    public void setReservedApt(ArrayList<Pair<LocalDateTime, LocalDateTime>> reservedApt) {
        this.reservedApt = reservedApt;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
