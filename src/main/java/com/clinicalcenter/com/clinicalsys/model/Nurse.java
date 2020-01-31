package com.clinicalcenter.com.clinicalsys.model;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "nurse")
public class Nurse extends Staff{

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Recipe> recipes;

    public Nurse() {
    }
}
