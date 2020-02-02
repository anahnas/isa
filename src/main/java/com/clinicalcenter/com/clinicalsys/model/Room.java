package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.RoomRepository;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;

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

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> future_appointments;

    public Room() {
    }

    public Room(String name, RoomType type) {
        this.name = name;
        this.type = type;
        future_appointments = new HashSet<Appointment>();
    }

    /*returns true if it added apt successfully, or false if the room was taken in given time span*/
    //TODO what if Date is null, cover that edge case
    public boolean addAppointment(Appointment apt){
        Date start = apt.getStartTime();
        Date end = apt.getEndTime();
        for (Appointment future_appointment: future_appointments){
            Date start2 = future_appointment.getStartTime();
            Date end2 = future_appointment.getEndTime();
            if(start.after(start2)&&start.before(end2)||(end.after(start2)&&end.before(end2))){
                return false;
            }
            if(start2.after(start)&&start2.before(end)){
                return false;
            }
        }
        future_appointments.add(apt);
        return true;
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
}
