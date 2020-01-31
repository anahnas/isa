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
    private Date startTime;

    @Column(name = "endTime", unique = false, nullable = false)
    private Date endTime;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppointmentType type;

    @Column(name = "appState", unique = false, nullable = false)
    private AppStateEnum appState;

    /*proveriti jos da li je dobro onetone ili nesto drugo*/
    @ManyToOne(fetch = FetchType.EAGER)
    private Patient patient;

    @ManyToOne(fetch = FetchType.EAGER)
    private Doctor doctor;

    @ManyToOne(fetch = FetchType.EAGER)
    private Room room;

    public Appointment() {
    }

    public Appointment(Date startTime, Date endTime, AppointmentType type, Patient patient, Room room, Doctor doctor) {
        this.startTime = startTime;
        this.type = type;
        this.appState=AppStateEnum.REQUESTED;
        this.patient = patient;
        this.doctor = doctor;
        this.room = room;
    }

}
