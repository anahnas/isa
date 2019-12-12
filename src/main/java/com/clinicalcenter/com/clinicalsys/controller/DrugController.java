package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Drug;
import com.clinicalcenter.com.clinicalsys.repository.DrugRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class DrugController {
    private final DrugRepository drugRepository;

    public DrugController(DrugRepository drugRepository) {
        this.drugRepository = drugRepository;
    }

    @PostMapping("/adddrug")
    public ResponseEntity<String> addDrug(@RequestBody Drug drug) {

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
        Set<Drug> retValue = drugRepository.allDrugs();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

}
