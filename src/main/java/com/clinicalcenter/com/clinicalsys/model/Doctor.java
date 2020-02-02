package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Calendar;
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
    public Boolean hasFreeAppointments(Date asked_date) {
        //TODO check if on vaccation
        Calendar date = Calendar.getInstance();
        date.setTime(asked_date);
        date.set(Calendar.HOUR, 8);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        Calendar final_app= Calendar.getInstance();
        final_app.setTime(asked_date);
        final_app.set(Calendar.HOUR, 15);
        final_app.set(Calendar.MINUTE, 30);
        final_app.set(Calendar.SECOND, 0);
        while(date.compareTo(final_app)<0){
            Calendar endTime= (Calendar) date.clone();
            endTime.add(Calendar.MINUTE,30);
            if(checkIfAppFree(date,endTime)){
                return Boolean.TRUE;
            }
            date.add(Calendar.MINUTE, 30);
        }
        return Boolean.FALSE;
    }

    private Boolean checkIfAppFree(Calendar start, Calendar end){
        for (Appointment apt: this.getAppointments()){
            Calendar start2 = Calendar.getInstance();
            start2.setTime(apt.getStartTime());
            Calendar end2 = Calendar.getInstance();
            end2.setTime(apt.getEndTime());
            int t=start.compareTo(start2);
            int t1=start.compareTo(end2);
            int t2=end.compareTo(start2);
            int t3=end.compareTo(start2);
            if(start.compareTo(start2)>=0&&start.compareTo(end2)<0||
                    (end.compareTo(start2)>0&&end.compareTo(end2)<=0)){
                return Boolean.FALSE;
            }
            if(start2.compareTo(start)>0&&start2.compareTo(end)<0){
                return Boolean.FALSE;
            }
        }
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

    public Set<AppointmentType> getSpecializations() {
        return specializations;
    }

    public void setSpecializations(Set<AppointmentType> specializations) {
        this.specializations = specializations;
    }

    //endregion
}
