package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "AppRequirement")
public class AppointReq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateTime", unique = false, nullable = false)
    private Long dateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private AppointmentType type;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Doctor doctor;

    @Column(name = "appState", unique = false, nullable = false)
    private AppStateEnum appState;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private User applicant;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Clinic clinic;

    public AppointReq() {
    }
}
