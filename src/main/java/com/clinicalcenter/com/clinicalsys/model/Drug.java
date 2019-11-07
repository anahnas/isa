package com.clinicalcenter.com.clinicalsys.model;

public class Drug {
    private String drugId;
    private String drugName;
    private double price;

    public Drug() {
    }

    public Drug(String drugId, String drugName, double price) {
        this.drugId = drugId;
        this.drugName = drugName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDrugId() {
        return drugId;
    }

    public void setDrugId(String drugId) {
        this.drugId = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
