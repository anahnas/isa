package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "businessReport")
public class ClinicReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "clinicRating", unique = false, nullable = false)
    private Float clinicRating;

    @Column(name = "doctorRating", unique = false, nullable = false)
    private Float doctorRating;

    @Column(name = "graphic", unique = false, nullable = false)
    private Float graphic;

    @Column(name = "income", unique = false, nullable = false)
    private Float income;

    //@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    //private Clinic clinic;

    public ClinicReport() {
    }
}
