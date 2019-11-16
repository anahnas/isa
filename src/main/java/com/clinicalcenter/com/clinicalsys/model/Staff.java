package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "staff")
public class Staff extends User {

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Patient> patients;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Clinic clinic;

   /* @Column(name = "calendar")
    private Calendar calendar;*/

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<VacationRequest> vacReq;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> appointments;

    public Staff() {
    }
}
