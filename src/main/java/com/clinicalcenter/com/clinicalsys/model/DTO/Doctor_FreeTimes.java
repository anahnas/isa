package com.clinicalcenter.com.clinicalsys.model.DTO;

import com.clinicalcenter.com.clinicalsys.model.Doctor;

import java.util.Set;

public class Doctor_FreeTimes {

    private Doctor doctor;
    private Set<String> freeTimes;

    public Doctor_FreeTimes(){

    }

    public Doctor_FreeTimes(Doctor doctor, Set<String> freeTimes){
        this.doctor = doctor;
        this.freeTimes = freeTimes;
    }

    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Set<String> getFreeTimes() {
        return freeTimes;
    }

    public void setFreeTimes(Set<String> freeTimes) {
        this.freeTimes = freeTimes;
    }
}
