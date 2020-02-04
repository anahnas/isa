package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.InheritanceType.JOINED;

@Setter
@Getter
@Entity
@Table(name = "appointment_type_price_discount")
@Inheritance(strategy = JOINED)
public class AppointmentType_Price_Discount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    private AppointmentType appointmentType;

    @Column(name = "price", nullable = false)
    private Double price;

    @Column(name = "discound", nullable = false)
    private Double discount;

    public AppointmentType_Price_Discount() {
    }

    public AppointmentType_Price_Discount(AppointmentType at, Double price, Double discount) {
        this.appointmentType = at;
        this.price = price;
        this.discount = discount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public AppointmentType getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(AppointmentType appointmentType) {
        this.appointmentType = appointmentType;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }
}
