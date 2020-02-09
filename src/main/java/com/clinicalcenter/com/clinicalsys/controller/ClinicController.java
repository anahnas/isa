package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class ClinicController {

    private final ClinicRespository clinicRespository;


    public ClinicController(ClinicRespository clinicRespository) {
        this.clinicRespository = clinicRespository;
    }

    @PostMapping("/clinics")
    public ResponseEntity<String> addClinic(@RequestBody Clinic clinic) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println(clinic.getClinicName() + " " + clinic.getAddress());
        clinic.setDescription("Opis");
        clinic.setRating(5.5);
        clinicRespository.save(clinic);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/getclinics")
    public ResponseEntity<Set<Clinic>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Clinic> retValue = clinicRespository.findClinics();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getclinicsByCA")
    public ResponseEntity<Set<Clinic>> getRequestsByCA(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Clinic> retValue = clinicRespository.findClinics();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }


}
