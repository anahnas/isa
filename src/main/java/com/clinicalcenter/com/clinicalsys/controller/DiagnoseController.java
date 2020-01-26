package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Diagnose;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.DiagnoseRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DiagnoseController {

    private final DiagnoseRepository diagnoseRepository;

    public DiagnoseController(DiagnoseRepository diagnoseRepository) {
        this.diagnoseRepository = diagnoseRepository;
    }


    @PostMapping("/createDiagnose")
    public ResponseEntity<String> createDiagnose(@RequestBody Diagnose diagnose){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Diagnose there  = this.diagnoseRepository.findByName(diagnose.getName());
        if(there == null){
            this.diagnoseRepository.save(diagnose);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
        else{
            return new ResponseEntity<>("Already exist", HttpStatus.BAD_REQUEST);
        }
    }
}
