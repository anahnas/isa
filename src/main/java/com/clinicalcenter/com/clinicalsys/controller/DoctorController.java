package com.clinicalcenter.com.clinicalsys.controller;


import com.clinicalcenter.com.clinicalsys.model.Drug;
import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.Recipe;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.DrugRepository;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.repository.RecipeRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/doctor")
public class DoctorController {
    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final RecipeRepository recipeRepository;
    private final DrugRepository drugRepository;

    public DoctorController(UserRepository userRepository, PatientRepository patientRepository, RecipeRepository recipeRepository, DrugRepository drugRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.recipeRepository = recipeRepository;
        this.drugRepository = drugRepository;
    }

    @GetMapping("/getpatients")
    public ResponseEntity<Set<User>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<User> retValue = userRepository.allPatients();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> getUser(@RequestParam String email){

        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User u = userRepository.findByEmail(email);
        if(u != null){
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/makerecipe/{email}")
    public ResponseEntity<String> makeRecipe(@RequestBody Drug drug, @PathVariable("email") String email){
        System.out.println("Usaooo->"+email);
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            Drug dr = drugRepository.findDrugByDrugName(drug.getDrugName());
            Recipe r = new Recipe();
            r.setDrug(dr);
            r.setPatient((Patient) user);
            this.recipeRepository.save(r);
            return new ResponseEntity<>("", HttpStatus.OK);


        }
    }
}