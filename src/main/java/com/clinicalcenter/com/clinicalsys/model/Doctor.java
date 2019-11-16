package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends Staff {

    @Column(name = "rating", unique = false, nullable = false)
    private Float rating;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AppointReq> appReq;
}
