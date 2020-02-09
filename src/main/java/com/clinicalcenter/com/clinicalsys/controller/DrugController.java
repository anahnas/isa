package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Drug;
import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.Recipe;
import com.clinicalcenter.com.clinicalsys.model.Nurse;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentRepository;
import com.clinicalcenter.com.clinicalsys.repository.DrugRepository;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.repository.RecipeRepository;
import com.clinicalcenter.com.clinicalsys.repository.NurseRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class DrugController {
    private final DrugRepository drugRepository;
    private final UserRepository userRepository;

    @Autowired
    private final AppointmentRepository appointmentRepository;

    private final RecipeRepository recipeRepository;

    private final PatientRepository patientRepository;
    public DrugController(DrugRepository drugRepository, AppointmentRepository appointmentRepository, RecipeRepository recipeRepository, PatientRepository patientRepository, UserRepository userRepository) {
        this.drugRepository = drugRepository;
        this.userRepository = userRepository;
        this.appointmentRepository = appointmentRepository;
        this.recipeRepository = recipeRepository;
        this.patientRepository = patientRepository;
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

    @GetMapping("/drugs/getalldrugs")
    public ResponseEntity<Set<Drug>> getDrugs(){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        Set<Drug> retValue = drugRepository.allDrugs();

        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(retValue, HttpStatus.OK);

    }

    @GetMapping("/drugs/getallNurses")
    public ResponseEntity<Set<User>> getNurse(){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<User> retValue = userRepository.findNurses();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(retValue, HttpStatus.OK);
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

    @PostMapping("/drugs/drugToPatient/{appId}/{drugId}")
    public ResponseEntity<String> drugToPatient(@PathVariable("appId") String appId, @PathVariable("drugId") String drugId) {
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Drug d = drugRepository.findByIdMy(Long.parseLong(drugId));
        if (d == null){
            return new ResponseEntity<>("Drug not found", HttpStatus.NOT_FOUND);
        }
        Appointment a = appointmentRepository.findByIdMy(Long.parseLong(appId));
        if (a == null){
            return new ResponseEntity<>("Appointment not found", HttpStatus.NOT_FOUND);
        }

        Patient p = this.patientRepository.findByEmail(a.getPatient().getEmail());
        if (p == null){
            return new ResponseEntity<>("Patient not found", HttpStatus.NOT_FOUND);
        }
        Recipe r = new Recipe(d, null, p);
        r = recipeRepository.save(r);
        p.getMedicalRecord().addRecipe(r);
        patientRepository.save(p);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

}
