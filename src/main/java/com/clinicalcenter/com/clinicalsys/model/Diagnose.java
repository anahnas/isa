package com.clinicalcenter.com.clinicalsys.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "diagnose")
public class Diagnose
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = false, nullable = false)
    private String name;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private DiagnoseRecord diagnoseRecord;

    public Diagnose() {
    }

   /* public Diagnose(String name) {

        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long diagnoseID) {
        this.id = diagnoseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }*/
}
