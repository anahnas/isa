package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Setter
@Getter
@Entity
@Table(name = "diagnoseRecord")
public class DiagnoseRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Diagnose> diagnoses;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private ClinicalCenter clinicalCenter;


    public DiagnoseRecord() {
    }
}
