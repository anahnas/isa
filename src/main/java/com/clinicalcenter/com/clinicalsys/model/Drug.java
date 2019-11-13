package com.clinicalcenter.com.clinicalsys.model;

public class Drug {

    private Long id;
    private String drugName;
    private double price;

    public Drug() {
    }

    public Drug(String drugName, double price) {

        this.drugName = drugName;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getDrugId() {
        return id;
    }

    public void setId(Long drugId) {
        this.id = drugId;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
