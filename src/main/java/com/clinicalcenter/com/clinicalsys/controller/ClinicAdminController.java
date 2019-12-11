package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.ClinicAdminRepository;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ClinicAdminController {
    private final UserRepository userRepository;
    private final ClinicRespository clinicRespository;
    private final ClinicAdminRepository caRep;

    public ClinicAdminController(UserRepository userRepository, ClinicRespository clinicRespository, ClinicAdminRepository caRep) {
        this.userRepository = userRepository;
        this.clinicRespository = clinicRespository;
        this.caRep = caRep;
    }

    @PostMapping("/regClinicAdmin/{clinic}")
    public ResponseEntity<String> addUser(@RequestBody User user, @PathVariable("clinic") String clinic) {

        Clinic clc = clinicRespository.findByClinicName(clinic);
        System.out.println("Ovo je klinika koju treba obraditi: "+clinic);
        user.setActive(Boolean.TRUE);
        user.setAdminConfirmed(Boolean.TRUE);
        user.setRole(RoleEnum.CLINIC_ADMIN);
        ClinicAdmin ca = new ClinicAdmin();
        ca.setFirstName(user.getFirstName());
        ca.setLastName(user.getLastName());
        ca.setPassword(user.getPassword());
        ca.setEmail(user.getEmail());
        ca.setActive(user.getActive());
        ca.setAdminConfirmed(user.getAdminConfirmed());
        ca.setSsn(user.getSsn());
        ca.setClinic(clc);
        caRep.save(ca);
        //userRepository.save(user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
