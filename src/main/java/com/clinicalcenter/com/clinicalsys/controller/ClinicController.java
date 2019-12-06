package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicController {

    private final ClinicRespository clinicRespository;


    public ClinicController(ClinicRespository clinicRespository) {
        this.clinicRespository = clinicRespository;
    }

    @PostMapping("/clinics")
    public ResponseEntity<String> addClinic(@RequestBody Clinic clinic) {
        System.out.println(clinic.getClinicName() + " " + clinic.getAddress());
        clinic.setDescription("Opis");
        clinic.setRating(5.5f);
        clinicRespository.save(clinic);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @GetMapping("/getclinics")
    public ResponseEntity<Set<Clinic>> getRequests(){
        Set<Clinic> retValue = clinicRespository.findRequests();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }


}
