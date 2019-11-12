package com.clinicalcenter.com.clinicalsys.model;

import java.util.AbstractMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
enum TYPE {SURGERY, EXAMINATION}
public class Room {
    private String id;
    private ArrayList<AbstractMap<LocalDateTime,LocalDateTime>> reservedApt;
    private TYPE type;

    public Room() {
        reservedApt = new ArrayList<AbstractMap<LocalDateTime, LocalDateTime>>();
    }

    public Room(String id, TYPE type) {
        this.id = id;
        this.type = type;
        reservedApt = new ArrayList<AbstractMap<LocalDateTime, LocalDateTime>>();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public ArrayList<AbstractMap<LocalDateTime, LocalDateTime>> getReservedApt() {
        return reservedApt;
    }

    public void setReservedApt(ArrayList<AbstractMap<LocalDateTime, LocalDateTime>> reservedApt) {
        this.reservedApt = reservedApt;
    }

    public TYPE getType() {
        return type;
    }

    public void setType(TYPE type) {
        this.type = type;
    }
}
