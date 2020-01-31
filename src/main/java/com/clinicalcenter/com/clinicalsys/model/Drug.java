package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Setter
@Getter
@Entity
@Table(name = "drug")
public class Drug {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "drugName", unique = true, length=30, nullable = false)
    private String drugName;

    @Column(name = "description", unique = false, nullable = false)
    private String description;

    @Column(name = "price", unique = false, nullable = false)
    private Double price;

    public Drug() {
    }

    public Drug(String drugName, String description, Double price) {
        this.description = description;
        this.drugName = drugName;
        this.price = price;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

}
