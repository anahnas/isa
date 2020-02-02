package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import com.clinicalcenter.com.clinicalsys.model.Doctor;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentTypeRepository;
import com.clinicalcenter.com.clinicalsys.repository.DoctorRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;

    public PatientController(DoctorRepository doctorRepository, AppointmentTypeRepository appointmentTypeRepository){
        this.doctorRepository=doctorRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
    }

    @GetMapping("/getDoctors")
    public ResponseEntity<Set<Doctor>> getDoctors(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Doctor> retValue= (Set<Doctor>) doctorRepository.findAll();
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getAppointmentTypes")
    public ResponseEntity<Set<AppointmentType>> getAppointmentTypes(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<AppointmentType> retValue= new HashSet<AppointmentType>(appointmentTypeRepository.findAll());
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }
}
