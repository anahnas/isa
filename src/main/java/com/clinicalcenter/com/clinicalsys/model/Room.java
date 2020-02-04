package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.RoomRepository;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

import java.util.Calendar;
import java.util.Date;
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

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> future_appointments;

    @JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Surgery> future_surgeries;

    public Room() {
    }

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
        future_appointments = new HashSet<Appointment>();
        future_surgeries = new HashSet<Surgery>();
    }

    /*returns true if it added apt successfully, or false if the room was taken in given time span*/
    //TODO what if Date is null, cover that edge case
    public boolean addAppointment(Appointment apt) {
        Date start = apt.getStartTime();
        Date end = apt.getEndTime();
        if(isFree(start,end)){
            future_appointments.add(apt);
            return true;
        }
        return false;
    }
    public boolean addSurgery(Surgery surg) {
        Date start = surg.getStartTime();
        Date end = surg.getEndTime();
        if(isFree(start,end)){
            future_surgeries.add(surg);
            return true;
        }
        return false;
    }

    public boolean isFree(Date startd, Date endd) {
        Calendar start=Calendar.getInstance();
        start.setTime(startd);
        Calendar end=Calendar.getInstance();
        end.setTime(endd);
        for (Appointment apt : this.getFuture_appointments()) {
            Calendar start2 = Calendar.getInstance();
            start2.setTime(apt.getStartTime());
            Calendar end2 = Calendar.getInstance();
            end2.setTime(apt.getEndTime());
            if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0 ||
                    (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
                return Boolean.FALSE;
            }
            if (start2.compareTo(start) > 0 && start2.compareTo(end) < 0) {
                return Boolean.FALSE;
            }
        }
        for (Surgery srg : this.getFuture_surgeries()) {
            Calendar start2 = Calendar.getInstance();
            start2.setTime(srg.getStartTime());
            Calendar end2 = Calendar.getInstance();
            end2.setTime(srg.getEndTime());
            if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0 ||
                    (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
                return Boolean.FALSE;
            }
            if (start2.compareTo(start) > 0 && start2.compareTo(end) < 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
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

    public Set<Surgery> getFuture_surgeries() {
        return future_surgeries;
    }

    public void setFuture_surgeries(Set<Surgery> future_surgeries) {
        this.future_surgeries = future_surgeries;
    }
}
