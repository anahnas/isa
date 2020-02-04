package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.DTO.Doctor_FreeTimes;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyAdminsServis;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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
    private NotifyAdminsServis notifyAdminsServis;

    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private ClinicRespository clinicRespository;
    @Autowired
    private ClinicAdminRepository clinicAdminRespository;
    @Autowired
    private PatientRepository patientRespository;

    public PatientController(DoctorRepository doctorRepository, AppointmentTypeRepository appointmentTypeRepository,
                             ClinicRespository clinicRespository, PatientRepository patientRepository,
                             AppointmentRepository appointmentRepository,ClinicAdminRepository clinicAminRespository,
                             NotifyAdminsServis notifyAdminsServis){
        this.doctorRepository=doctorRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.clinicRespository = clinicRespository;
        this.patientRespository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.clinicAdminRespository = clinicAminRespository;
        this.notifyAdminsServis = notifyAdminsServis;
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
            if(doctor.getFreeTimes(date).isEmpty())
                continue;
            if(!clinics.contains(doctor.getClinic())){
                clinics.add(doctor.getClinic());
            }
        }
        return new ResponseEntity<>(clinics,HttpStatus.OK);
    }

    /*Receives Date,AppointmentType and CLinic name, returns all doctors that work for that clinic,
     are free on that date and specialise in those AppointmentTypes + all their free times*/
    @PostMapping("/getAvailableClinics/{date}/{clinicName}")
    public ResponseEntity<Set<Doctor_FreeTimes>> getFreeSpecializedDoctors(@RequestBody AppointmentType appType,
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
        Set<Doctor_FreeTimes> retVal=new HashSet<>();
        for(Doctor doctor : doctors_temp){
            Set<String> freeTimes = doctor.getFreeTimes(date);
            if(freeTimes.isEmpty())
                continue;
            retVal.add(new Doctor_FreeTimes(doctor,freeTimes));
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    /*Receives Date,AppointmentType and CLinic name, returns all doctors that work for that clinic,
    are free on that date and specialise in those AppointmentTypes*/
    /*IMPORTANT NOTE patient email ends wit .com ili .net ili tako nesto, sto se uopste ne parsira, pa se na kraj
     * mora dadati jos jedna tacka!!!*/
    @PostMapping("/requestApp/{date}/{doctorEmail}/{patientEmail}")
    public ResponseEntity<String> requestApp(@RequestBody AppointmentType appType,
                                                                 @PathVariable("date") String date_string,
                                                                 @PathVariable("doctorEmail") String doctor_email,
                                                                 @PathVariable("patientEmail") String patient_email) {
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Doctor doctor = doctorRepository.findByEmail(doctor_email);
        Patient patient = patientRespository.findByEmail(patient_email);
        Calendar end_time = Calendar.getInstance();
        end_time.setTime(date);
        end_time.add(Calendar.MINUTE,30);
        Appointment requestedApp = new Appointment(date, end_time.getTime(),appType,patient,null,doctor);
        requestedApp.setAppState(AppStateEnum.REQUESTED);
        requestedApp = appointmentRepository.save(requestedApp);
        Set<ClinicAdmin> clinicAdmins = clinicAdminRespository.getByDoctorEmail(doctor_email);
        Appointment finalRequestedApp = requestedApp;
        new Thread(() -> {
            for (ClinicAdmin admin:clinicAdmins){
                admin.getAppointments_to_process().add(finalRequestedApp);
                admin = clinicAdminRespository.save(admin);
                notifyAdminsServis.newRequestNotification(admin,true);
            }
        }).start();
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    
}
