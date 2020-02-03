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

    @OneToMany(fetch = FetchType.LAZY)
    private Set<Recipe> recipes;

    public Nurse() {
        recipes = new HashSet<Recipe>();
    }

    public Nurse(Staff s) {
        super(s);
        recipes = new HashSet<Recipe>();
        this.setActive(Boolean.TRUE);
        this.setAdminConfirmed(Boolean.TRUE);
        this.setRole(RoleEnum.NURSE);
        this.setFirstLogin(Boolean.TRUE);
    }

    public void addRecipe(Recipe recipe) {
        recipes.add(recipe);
    }
}
