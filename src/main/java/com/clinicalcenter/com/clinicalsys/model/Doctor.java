package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Setter
@Entity
@Table(name = "doctor")
public class Doctor extends Staff {

    @Column(name = "rating", unique = false, nullable = true)
    private Double rating;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @OneToMany(fetch = FetchType.EAGER)
    private Set<Appointment> requested;

    @com.fasterxml.jackson.annotation.JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    private Set<AppointmentType> specializations;

    public Doctor() {

    }

    public Doctor(Staff staff, Double rating) {
        super(staff);
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setRole(RoleEnum.DOCTOR);
        this.setFirstLogin(Boolean.TRUE);
        this.rating = rating;
        requested = new HashSet<Appointment>();
        specializations = new HashSet<AppointmentType>();
    }

    /*Takes in a day and checks if doctor is free on that day*/
    public Set<String> getFreeTimes(Date asked_date) {
        //TODO check if on vaccation

        Set<String> freeTimes = new HashSet<String>();

        Calendar date = Calendar.getInstance();
        date.setTime(asked_date);
        date.set(Calendar.HOUR_OF_DAY, 8);
        date.set(Calendar.MINUTE, 0);
        date.set(Calendar.SECOND, 0);
        Calendar final_app = Calendar.getInstance();
        final_app.setTime(asked_date);
        final_app.set(Calendar.HOUR_OF_DAY, 15);
        final_app.set(Calendar.MINUTE, 30);
        final_app.set(Calendar.SECOND, 0);
        while (date.compareTo(final_app) < 0) {
            Calendar endTime = (Calendar) date.clone();
            endTime.add(Calendar.MINUTE, 30);
            if (checkIfAppFree(date, endTime)) {
                String str = String.valueOf(date.get(Calendar.HOUR_OF_DAY)) + ":" + String.valueOf(date.get(Calendar.MINUTE));
                if (date.get(Calendar.HOUR_OF_DAY) < 10) {
                    str = "0" + str;
                }
                if (date.get(Calendar.MINUTE) == 0) {
                    str += "0";
                }
                freeTimes.add(str);
            }
            date.add(Calendar.MINUTE, 30);
        }
        TreeSet<String> sortedTimes = new TreeSet<String>(freeTimes);
        return sortedTimes;
    }

    public Boolean checkIfAppFree(Calendar start, Calendar end) {
        for (Appointment apt : this.getAppointments()) {
            Calendar start2 = Calendar.getInstance();
            start2.setTime(apt.getStartTime());
            Calendar end2 = Calendar.getInstance();
            end2.setTime(apt.getEndTime());
            if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0 ||
                    (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
                return Boolean.FALSE;
            }
            if (start2.compareTo(start) > 0 && start2.compareTo(end) < 0) {
                return Boolean.FALSE;
            }
        }
        for (Surgery srg : this.getSurgeries()) {
            Calendar start2 = Calendar.getInstance();
            start2.setTime(srg.getStartTime());
            Calendar end2 = Calendar.getInstance();
            end2.setTime(srg.getEndTime());
            if (start.compareTo(start2) >= 0 && start.compareTo(end2) < 0 ||
                    (end.compareTo(start2) > 0 && end.compareTo(end2) <= 0)) {
                return Boolean.FALSE;
            }
            if (start2.compareTo(start) > 0 && start2.compareTo(end) < 0) {
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public void addSpecialisation(AppointmentType at) {
        if (!this.specializations.contains(at)) {
            this.specializations.add(at);
        }
    }

    //region getters/setters
    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
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
