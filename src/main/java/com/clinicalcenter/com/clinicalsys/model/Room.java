package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.AbstractMap;

import java.time.LocalDateTime;
import java.util.ArrayList;
enum TYPE {SURGERY, EXAMINATION}

@Getter
@Setter
@Entity
@Table(name = "room")
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "number", nullable = false)
    private Integer number;

    //private ArrayList<AbstractMap<LocalDateTime,LocalDateTime>> reservedApt;

    @Column(name = "type", nullable = false)
    private TYPE type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Clinic clinic;

    public Room() {
    }

    /*public Room() {
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
    }*/

}
