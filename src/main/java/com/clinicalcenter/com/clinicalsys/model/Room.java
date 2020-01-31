package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

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

    @Column(name = "type", nullable = false)
    private RoomType type;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> future_appointments;

    public Room() {
    }

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
        future_appointments = new HashSet<Appointment>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public Set<Appointment> getFuture_appointments() {
        return future_appointments;
    }

    public void setFuture_appointments(Set<Appointment> future_appointments) {
        this.future_appointments = future_appointments;
    }
}
