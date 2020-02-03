package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Staff;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.VacationRequest;
import com.clinicalcenter.com.clinicalsys.repository.StaffRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

@RestController
public class StaffController {
    private final UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;

    public StaffController(UserRepository userRepository, StaffRepository staffRepository) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
    }



    @GetMapping("/getStaff")
    public ResponseEntity<Set<User>> getRequests(){
        //TODO aktivirati autorizaciju za ovu metodu
        /*if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }*/
        System.out.println("StaffController");
        Set<User> retValue = userRepository.findStaff();
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getAppointments/{email}")
    public ResponseEntity<Set<Appointment>> getAppointments(@PathVariable("email") String email){
        Staff staff = staffRepository.findByEmail(email);
        return new ResponseEntity<>(staff.getAppointments(), HttpStatus.OK);
    }

    @GetMapping("/getVacations/{email}")
    public ResponseEntity<Set<VacationRequest>> getVacations(@PathVariable("email") String email){
        Staff staff = staffRepository.findByEmail(email);
        return new ResponseEntity<>(staff.getVacReq(), HttpStatus.OK);
    }
}
