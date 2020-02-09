package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.Diagnose;
import com.clinicalcenter.com.clinicalsys.model.Drug;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentRepository;
import com.clinicalcenter.com.clinicalsys.repository.DiagnoseRepository;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import jdk.jshell.Diag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class DiagnoseController {

    private final DiagnoseRepository diagnoseRepository;
    private final AppointmentRepository appointmentRepository;
    private final PatientRepository patientRepository;
    public DiagnoseController(DiagnoseRepository diagnoseRepository, AppointmentRepository appointmentRepository, PatientRepository patientRepository) {
        this.diagnoseRepository = diagnoseRepository;
        this.appointmentRepository = appointmentRepository;
        this.patientRepository = patientRepository;
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

    @GetMapping("/getDiagnoses")
    public ResponseEntity<Set<Diagnose>> getDiagnoses(){

        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Set<Diagnose> retValue = diagnoseRepository.allDiagnoses();

        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(retValue, HttpStatus.OK);

    }

    @PostMapping("/diagnoseToPatient/{appId}/{drugId}")
    public ResponseEntity<String> drugToPatient(@PathVariable("appId") String appId, @PathVariable("drugId") String drugId) {
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Diagnose d = diagnoseRepository.findByIdMy(Long.parseLong(drugId));
        if (d == null){
            return new ResponseEntity<>("Diagnose not found", HttpStatus.NOT_FOUND);
        }
        Appointment a = appointmentRepository.findByIdMy(Long.parseLong(appId));
        if (a == null){
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }

        Patient p = this.patientRepository.findByEmail(a.getPatient().getEmail());
        if (p == null){
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
        p.getMedicalRecord().getDiagnoses().add(d);
        patientRepository.save(p);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
