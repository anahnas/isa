package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "clinicAdmin")
public class ClinicAdmin extends User{

    @ManyToOne(fetch = FetchType.EAGER)
    private Clinic clinic;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<VacationRequest> vacations_to_process;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Appointment> appointments_to_process;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Surgery> surgeries_to_process;

    public ClinicAdmin() {
    }

    public ClinicAdmin(User u, Clinic clc){
        super(u);
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setRole(RoleEnum.CLINIC_ADMIN);
        this.setFirstLogin(Boolean.TRUE);
        this.clinic = clc;
        this.vacations_to_process=new HashSet<>();
        this.surgeries_to_process=new HashSet<>();
        this.appointments_to_process=new HashSet<>();
    }

    //region getters setters
    public Clinic getClinic() {
        return clinic;
    }

    public void setClinic(Clinic clinic) {
        this.clinic = clinic;
    }

    public Set<VacationRequest> getVacations_to_process() {
        return vacations_to_process;
    }

    public void setVacations_to_process(Set<VacationRequest> vacations_to_process) {
        this.vacations_to_process = vacations_to_process;
    }

    public Set<Appointment> getAppointments_to_process() {
        return appointments_to_process;
    }

    public void setAppointments_to_process(Set<Appointment> appointments_to_process) {
        this.appointments_to_process = appointments_to_process;
    }

    public Set<Surgery> getSurgeries_to_process() {
        return surgeries_to_process;
    }

    public void setSurgeries_to_process(Set<Surgery> surgeries_to_process) {
        this.surgeries_to_process = surgeries_to_process;
    }
    //endregion
}
