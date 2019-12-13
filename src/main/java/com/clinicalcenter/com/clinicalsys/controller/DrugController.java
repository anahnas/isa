package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Drug;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.DrugRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DrugController {
    private final DrugRepository drugRepository;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @PostMapping("/adddrug")
    public ResponseEntity<String> addDrug(@RequestBody Drug drug) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Drug temp = drugRepository.findDrugByDrugName(drug.getDrugName());
        if(temp==null) {
            drugRepository.save(drug);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("This drug already exists", HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getdrugs")
    public ResponseEntity<Set<Drug>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Drug> retValue = drugRepository.allDrugs();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

}
