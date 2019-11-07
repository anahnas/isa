package com.clinicalcenter.com.clinicalsys.model;

public class Diagnose
{
    private String diagnoseID;
    private String name;

    public Diagnose() {
    }

    public Diagnose(String diagnoseID, String name) {
        this.diagnoseID = diagnoseID;
        this.name = name;
    }

    public String getDiagnoseID() {
        return diagnoseID;
    }

    public void setDiagnoseID(String diagnoseID) {
        this.diagnoseID = diagnoseID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
