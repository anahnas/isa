package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Staff;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.VacationRequest;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.StaffRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

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
        Set<User> retValue = new HashSet<User>();
        retValue = userRepository.findStaff();
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    /*@GetMapping("/getObligations/{email}")
    public ResponseEntity<Staff_Obligations> getObligations(@PathVariable("email") String email){

        Staff staff=staffRepository.findByEmail(email);
        if(staff==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        appointmentsstaff.getAppointments()
    }*/

    @PostMapping("/requestVacation/{email}")
    public ResponseEntity<String> requestVacation(@RequestBody VacationRequest vr,
                                                  @PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Staff staff=staffRepository.findByEmail(email);
        if(staff==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        staff.getVacReq().add(vr);
        staffRepository.save(staff);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

}
