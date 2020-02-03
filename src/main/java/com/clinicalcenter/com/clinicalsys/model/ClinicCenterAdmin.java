package com.clinicalcenter.com.clinicalsys.model;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "clinicCenterAdmin")
public class ClinicCenterAdmin extends User {

    @Column(name = "predefined", unique = false, nullable = true)
    private Boolean predefined;


    public ClinicCenterAdmin() {

    }

    public ClinicCenterAdmin(User u, boolean predefined) {
        super(u);
        this.predefined = predefined;
        this.setRole(RoleEnum.CLINIC_CENTER_ADMIN);
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setFirstLogin(Boolean.TRUE);
    }
}
