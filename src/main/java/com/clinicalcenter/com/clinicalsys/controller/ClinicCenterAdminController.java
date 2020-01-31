package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.ClinicAdminRepository;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController
public class ClinicCenterAdminController {
    private final UserRepository userRepository;
    private final ClinicRespository clinicRespository;
    private final ClinicAdminRepository caRep;

    public ClinicCenterAdminController(UserRepository userRepository, ClinicRespository clinicRespository, ClinicAdminRepository caRep) {
        this.userRepository = userRepository;
        this.clinicRespository = clinicRespository;
        this.caRep = caRep;
    }

    /*Creating new Clinic Admin*/
    @PostMapping("/regClinicAdmin/{clinic}")
    public ResponseEntity<String> addUser(@RequestBody User user, @PathVariable("clinic") String clinic) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Clinic clc = clinicRespository.findByClinicName(clinic);
        System.out.println("Ovo je klinika koju treba obraditi: "+clinic);
        ClinicAdmin ca = new ClinicAdmin(user, clc);
        caRep.save(ca);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/getadmins")
    public ResponseEntity<Set<User>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("ClinicAdminController");
        Set<User> retValue;
        retValue = userRepository.findClinicAdmins();

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }
}
