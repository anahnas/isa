package com.clinicalcenter.com.clinicalsys.controller;


import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.*;
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
    @Autowired
    private NurseRepository nurseRepository;

    public NurseController(RecipeRepository recipeRepository, PatientRepository patientRepository,
                           AppointmentRepository appointmentRepository, NurseRepository nurseRepository) {
        this.recipeRepository = recipeRepository;
        this.patientRepository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.nurseRepository = nurseRepository;
    }

    @GetMapping("/getRecipesForValidation/{email}")
    public ResponseEntity<Set<Recipe>> getRecipes(@PathVariable("email") String email){

        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Recipe> retVal = nurseRepository.getRecipesForValidation(email);
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @PostMapping("/validateRecipe/{id}")
    public ResponseEntity<String> validateRecipe(@PathVariable("id") String id){
        if(!Authorized.isAuthorised(RoleEnum.NURSE)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        try{
            Long l = Long.parseLong(id);
            recipeRepository.validate(l);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        catch (Exception e){
            return new ResponseEntity<>("nije pronadjen", HttpStatus.NOT_FOUND);
        }

    }
}
