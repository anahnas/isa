package com.clinicalcenter.com.clinicalsys.model;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends Staff {

    @Column(name = "rating", unique = false, nullable = true)
    private Float rating;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Appointment> requested;

    public Doctor(){

    }

    public Doctor(Staff staff){
        super(staff);
        rating = null;
        requested = new HashSet<Appointment>();
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public Set<Appointment> getRequested() {
        return requested;
    }

    public void setRequested(Set<Appointment> requested) {
        this.requested = requested;
    }
}
