package com.clinicalcenter.com.clinicalsys.model;


import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nurse")
public class Nurse extends Staff {

    public Nurse(){
    }

    public Nurse(Staff s) {
        super(s);
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setRole(RoleEnum.NURSE);
        this.setFirstLogin(Boolean.TRUE);
    }

}
