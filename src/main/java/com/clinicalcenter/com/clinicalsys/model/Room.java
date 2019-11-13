package com.clinicalcenter.com.clinicalsys.model;

import java.util.AbstractMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
enum TYPE {SURGERY, EXAMINATION}
public class Room {

    private Long id;
    private String name;
    private ArrayList<AbstractMap<LocalDateTime,LocalDateTime>> reservedApt;
    private TYPE type;

    public Room() {
        reservedApt = new ArrayList<AbstractMap<LocalDateTime, LocalDateTime>>();
    }

    public Room(String name, TYPE type) {
        this.name = name;
        this.type = type;
        reservedApt = new ArrayList<AbstractMap<LocalDateTime, LocalDateTime>>();
    }

    public void setType(TYPE type) {
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

}
