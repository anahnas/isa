package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.repository.ClinicAdminRepository;
import com.clinicalcenter.com.clinicalsys.repository.StaffRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.services.NotifyAdminsServices;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class StaffController {
    private final UserRepository userRepository;
    @Autowired
    private StaffRepository staffRepository;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    NotifyAdminsServices notifyAdminsServices;

    public StaffController(UserRepository userRepository, StaffRepository staffRepository,
                           ClinicAdminRepository clinicAdminRepository, NotifyAdminsServices notifyAdminsServices) {
        this.userRepository = userRepository;
        this.staffRepository = staffRepository;
        this.clinicAdminRepository = clinicAdminRepository;
        this.notifyAdminsServices = notifyAdminsServices;
    }



    @GetMapping("/getStaff")
    public ResponseEntity<Set<User>> getRequests(){
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

    @GetMapping("/getSurgeries/{email}")
    public ResponseEntity<Set<Surgery>> getSurgeries(@PathVariable("email") String email){
        Staff staff = staffRepository.findByEmail(email);
        return new ResponseEntity<>(staff.getSurgeries(), HttpStatus.OK);
    }

    @PostMapping("/requestVacation/{email}")
    public ResponseEntity<String> requestVacation(@RequestBody VacationRequest vr,
                                                  @PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        vr.setAccepted(false);
        vr.setStaff(staffRepository.findByEmail(email));
        Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(email);
        new Thread(() -> {
            for (ClinicAdmin admin : clinicAdmins) {
                admin.getVacations_to_process().add(vr);
                admin = clinicAdminRepository.save(admin);
                notifyAdminsServices.newRequestNotification(admin,Boolean.TRUE);
            }
        }).start();
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
