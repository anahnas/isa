package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name="clinicCenterAdmin")
public class ClinicCenterAdmin extends User{

    @Column(name = "predefined", unique = false, nullable = true)
    private Boolean predefined;


    public ClinicCenterAdmin() {
    }
}
