package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends Staff {

    @Column(name = "rating", unique = false, nullable = true)
    private Float rating;

    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> requested;

    @ManyToMany
    private Set<AppointmentType> specializations;

    public Doctor(){

    }

    public Doctor(Staff staff){
        super(staff);
        rating = null;
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setRole(RoleEnum.DOCTOR);
        this.setFirstLogin(Boolean.TRUE);
        requested = new HashSet<Appointment>();
        specializations = new HashSet<AppointmentType>();
    }

    /*Takes in a day and checks if doctor is free on that day*/
    public Boolean hasFreeAppointments(Date date) {
        //TODO
        return Boolean.TRUE;
    }

    public void addSpecialisation(AppointmentType at){
        if(!this.specializations.contains(at)){
            this.specializations.add(at);
        }
    }

    //region getters/setters
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
    //endregion
}
