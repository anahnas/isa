package com.clinicalcenter.com.clinicalsys.model;

public class Diagnose
{
    private Long id;
    private String name;

    public Diagnose() {
    }

    public Diagnose(String name) {

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
    }
}
