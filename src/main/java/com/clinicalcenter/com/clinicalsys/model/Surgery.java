package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "surgery")
public class Surgery {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "dateTime", nullable = false)
    private Long dateTime;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Room Room;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Doctor> doctors;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Patient patient;

    @Column(name = "startTime", nullable = false)
    private Long startTime;

    @Column(name = "endTime", nullable = false)
    private Long endTime;

    @Column(name = "duration", nullable = false)
    private Long duration;

    public Surgery() {
    }
}
