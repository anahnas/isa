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

    @Column(name = "drugName", unique = false, nullable = false)
    private String drugName;

    @Column(name = "description", unique = true, nullable = false)
    private String description;

    @Column(name = "price", unique = false, nullable = false)
    private double price;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private RecipeRecord recipeRecord;

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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public RecipeRecord getRecipeRecord() {
        return recipeRecord;
    }

    public void setRecipeRecord(RecipeRecord recipeRecord) {
        this.recipeRecord = recipeRecord;
    }

    public Drug() {
    }

   /* public Drug(String drugName, double price) {

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
    }*/
}
