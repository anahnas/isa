package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentTypeRepository;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import com.clinicalcenter.com.clinicalsys.repository.DoctorRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import com.clinicalcenter.com.clinicalsys.model.Doctor;
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
    @Autowired
    private ClinicRespository clinicRespository;

    public PatientController(DoctorRepository doctorRepository, AppointmentTypeRepository appointmentTypeRepository,
                             ClinicRespository clinicRespository){
        this.doctorRepository=doctorRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.clinicRespository = clinicRespository;
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

    /*Receives Date and AppointmentType, returns Clinics that have doctors
    free on that date that specialise in those AppointmentTypes*/
    @PostMapping("/getAvailableClinics/{date}")
    public ResponseEntity<Set<Clinic>> getFreeSpecializedDoctorClinics(@RequestBody AppointmentType appType, @PathVariable("date") String date_string) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Doctor> doctors= new HashSet<Doctor>(doctorRepository.allWithSpecialization(appType.getId()));
        Set<Clinic> clinics = new HashSet<Clinic>();
        for(Doctor doctor : doctors){
            if(!doctor.hasFreeAppointments(date))
                continue;
            if(!clinics.contains(doctor.getClinic())){
                clinics.add(doctor.getClinic());
            }
        }
        return new ResponseEntity<>(clinics,HttpStatus.OK);
    }

    /*Receives Date,AppointmentType and CLinic name, returns all doctors that work for that clinic,
     are free on that date and specialise in those AppointmentTypes*/
    @PostMapping("/getAvailableClinics/{date}/{clinicName}")
    public ResponseEntity<Set<Doctor>> getFreeSpecializedDoctors(@RequestBody AppointmentType appType,
                                                                 @PathVariable("date") String date_string,
                                                                 @PathVariable("clinicName") String clinic_name) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Clinic clinic = clinicRespository.findByClinicName(clinic_name);
        Set<Doctor> doctors_temp= new HashSet<Doctor>(doctorRepository.allWithSpecializationInClinic(appType.getId(),clinic.getId()));
        Set<Doctor> retVal=new HashSet<>();
        for(Doctor doctor : doctors_temp){
            if(!doctor.hasFreeAppointments(date))
                continue;
            retVal.add(doctor);
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    /*Receives Date,AppointmentType and CLinic name, returns all doctors that work for that clinic,
    are free on that date and specialise in those AppointmentTypes*/
  /*  @PostMapping("/requestApp/{date}/{doctorName}")
    public ResponseEntity<String> requestApp(@RequestBody AppointmentType appType,
                                                                 @PathVariable("date") String date_string,
                                                                 @PathVariable("doctorName") String dontor_name) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Clinic clinic = clinicRespository.findByClinicName(clinic_name);
        Set<Doctor> doctors_temp= new HashSet<Doctor>(doctorRepository.allWithSpecializationInClinic(appType.getId(),clinic.getId()));
        Set<Doctor> retVal=new HashSet<>();
        for(Doctor doctor : doctors_temp){
            if(!doctor.hasFreeAppointments(date))
                continue;
            retVal.add(doctor);
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }*/
}
