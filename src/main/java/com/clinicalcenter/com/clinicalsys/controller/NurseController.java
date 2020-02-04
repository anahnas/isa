package com.clinicalcenter.com.clinicalsys.controller;


import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentRepository;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.repository.RecipeRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigInteger;
import java.util.HashSet;
import java.util.Set;

@RestController
public class NurseController {

    private RecipeRepository recipeRepository;
    private PatientRepository patientRepository;
    private AppointmentRepository appointmentRepository;

    @Autowired
    private UserRepository userRepository;

    public NurseController(RecipeRepository recipeRepository, PatientRepository patientRepository, AppointmentRepository appointmentRepository) {
        this.recipeRepository = recipeRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
    }

    @GetMapping("/getRecipesForValidation")
    public ResponseEntity<Set<Recipe>> getRecipes(){

        if(!Authorized.isAuthorised(RoleEnum.NURSE)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Set<Recipe> recipes = new HashSet<>();
        Set<Patient> patients = patientRepository.getAllPatients();
        for(Patient p : patients){
            for(Recipe r : p.getMedicalRecord().getRecipes()){
                if(!r.isValidate()){
                    recipes.add(r);
                }
            }
        }
        if(recipes == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else{
            return new ResponseEntity<>(recipes, HttpStatus.OK);
        }
    }

    @PostMapping("/validateRecipe/{id}/{email}")
    public ResponseEntity<String> validateRecipe(@PathVariable("id") String id, @PathVariable("email") String email){
        if(!Authorized.isAuthorised(RoleEnum.NURSE)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try{
            Long l = Long.parseLong(id);
            User u = userRepository.findByEmail(email);
            System.out.println("Trazim->"+email);
            if(u==null){
                return new ResponseEntity<>("nije pronadjena sestrica", HttpStatus.NOT_FOUND);
            }
            recipeRepository.validate(l);
            Recipe r = recipeRepository.findRecipe(l);
            System.out.println(r.getDrug()+"found");
            r.setNurse((Nurse) u);
            System.out.println(((Nurse)u).getEmail()+"ovo je sestrica");
            recipeRepository.save(r);
            System.out.println("sacuvano");
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("nije pronadjen", HttpStatus.NOT_FOUND);
        }

    }
}
