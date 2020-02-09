package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.DTO.AppTypeDTO;
import com.clinicalcenter.com.clinicalsys.model.DTO.AppointmentSurgeryDTO;
import com.clinicalcenter.com.clinicalsys.model.DTO.Doctor_FreeTimes;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.AppointmentHandlingService;
import com.clinicalcenter.com.clinicalsys.services.NotifyAdminsServices;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import com.clinicalcenter.com.clinicalsys.model.Doctor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.lang.model.type.ArrayType;
import javax.persistence.EntityNotFoundException;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private AppointmentHandlingService appointmentHandlingService;
    @Autowired
    private NotifyAdminsServices notifyAdminsServices;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private ClinicRespository clinicRespository;
    @Autowired
    private ClinicAdminRepository clinicAdminRespository;
    @Autowired
    private PatientRepository patientRespository;

    public PatientController(DoctorRepository doctorRepository, AppointmentTypeRepository appointmentTypeRepository,
                             ClinicRespository clinicRespository, PatientRepository patientRepository,
                             AppointmentRepository appointmentRepository, ClinicAdminRepository clinicAminRespository,
                             NotifyAdminsServices notifyAdminsServices, SurgeryRepository surgeryRepository){
        this.doctorRepository=doctorRepository;
        this.appointmentTypeRepository = appointmentTypeRepository;
        this.clinicRespository = clinicRespository;
        this.patientRespository = patientRepository;
        this.appointmentRepository = appointmentRepository;
        this.clinicAdminRespository = clinicAminRespository;
        this.notifyAdminsServices = notifyAdminsServices;
        this.surgeryRepository = surgeryRepository;
    }

    @GetMapping("/getDoctors")
    public ResponseEntity<Set<Doctor>> getDoctors(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Doctor> retValue= (Set<Doctor>) doctorRepository.findAll();
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getDoctors/{clinic_id}")
    public ResponseEntity<Set<Doctor>> getDoctorsByClinic(@PathVariable("clinic_id") String clinicId){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<Doctor> retValue= doctorRepository.findAllInClinic(Long.parseLong(clinicId));
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getPredefinedAppointments/{clinic_id}")
    public ResponseEntity<Set<AppointmentSurgeryDTO>> getPredefinedAppointments(@PathVariable("clinic_id") String clinicId){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Clinic clinic = clinicRespository.findByIdMy(Long.parseLong(clinicId));
        Set<Appointment> appointments = clinic.getPredefinedAppointments();
        Set<AppointmentSurgeryDTO> retVal = new HashSet<AppointmentSurgeryDTO>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String type,doctor_name,clinic_name,strDate;
        Long id;
        for(Appointment appointment : appointments){
            type = appointment.getType().getType();
            doctor_name=appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName();
            clinic_name=appointment.getDoctor().getClinic().getClinicName();
            strDate = dateFormat.format(appointment.getStartTime());
            id = appointment.getId();
            Set<AppointmentType_Price_Discount> atpds = clinic.getAppointmentTypePriceDiscounts();
            Double price=null;
            Double discount=null;
            for(AppointmentType_Price_Discount atpd : atpds){
                if(atpd.getAppointmentType().getId()==appointment.getType().getId()){
                    price = atpd.getPrice();
                    discount = atpd.getDiscount();
                    break;
                }
            }
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, null,strDate,clinic_name,id,null,
                    null,null,null,null,price,discount));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @GetMapping("/getAllClinics")
    public ResponseEntity<Set<Clinic>> getAllClinics(){
        Set<Clinic> retVal = clinicRespository.findClinics();
        return new ResponseEntity<>(retVal, HttpStatus.OK);
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
    public ResponseEntity<Set<Clinic>> getFreeSpecializedDoctorClinics(@RequestBody AppTypeDTO appType, @PathVariable("date") String date_string) {

        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        Set<Clinic> clinics = new HashSet<Clinic>();
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(clinics, HttpStatus.NOT_ACCEPTABLE);
        }
        if(date.before(new Date())){
            return new ResponseEntity<>(clinics, HttpStatus.NOT_ACCEPTABLE);
        }
        if(appType.getType()==null){
            return new ResponseEntity<>(clinics, HttpStatus.NOT_ACCEPTABLE);
        }
        if(!appointmentRepository.existsById(appType.getId())) {
            return new ResponseEntity<>(clinics, HttpStatus.NOT_ACCEPTABLE);
        }
        Set<Doctor> doctors= new HashSet<Doctor>(doctorRepository.allWithSpecialization(appType.getId()));
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
    public ResponseEntity<Set<Doctor_FreeTimes>> getFreeSpecializedDoctors(@RequestBody AppTypeDTO appType,
                                                                           @PathVariable("date") String date_string,
                                                                           @PathVariable("clinicName") String clinic_name) {
        String pattern = "yyyy-MM-dd";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            System.out.println("1");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(date.before(new Date())){
            System.out.println("2");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(appType.getType()==null){
            System.out.println("3");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(!appointmentRepository.existsById(appType.getId())){
            System.out.println("4");
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
    @PostMapping("/requestApp/{date}/{doctorEmail}/{patientEmail}")
    public ResponseEntity<String> requestApp(@RequestBody AppTypeDTO appType,
                                                                 @PathVariable("date") String date_string,
                                                                 @PathVariable("doctorEmail") String doctor_email,
                                                                 @PathVariable("patientEmail") String patient_email) {
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)&&!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(date.before(new Date())){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(appType.getType()==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Doctor doctor = doctorRepository.findByEmail(doctor_email);
        if(doctor==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        Calendar end = (Calendar) start.clone();
        end.add(Calendar.MINUTE,30);
        if(!doctor.checkIfAppFree(start, end)){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Patient patient = patientRespository.findByEmail(patient_email);
        if(patient==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Calendar end_time = Calendar.getInstance();
        end_time.setTime(date);
        end_time.add(Calendar.MINUTE,30);
        AppointmentType appointmentType = appointmentTypeRepository.findByIdMy(appType.getId());
        Appointment requestedApp = new Appointment(date, end_time.getTime(),appointmentType,patient,null,doctor);
        requestedApp.setAppState(AppStateEnum.REQUESTED);
        requestedApp = appointmentRepository.save(requestedApp);
        Appointment finalRequestedApp = requestedApp;
        new Thread(() -> {
            Set<ClinicAdmin> clinicAdmins = clinicAdminRespository.getByDoctorEmail(doctor_email);
            for (ClinicAdmin admin:clinicAdmins){
                admin.getAppointments_to_process().add(finalRequestedApp);
                admin = clinicAdminRespository.save(admin);
                notifyAdminsServices.newRequestNotification(admin,Boolean.TRUE);
            }
        }).start();
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @GetMapping("/getPastAppointmentsAndSurgeries")
    public ResponseEntity<Set<AppointmentSurgeryDTO>> getPastAppointmentsAndSurgeries(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Patient patient = (Patient) ((MyUserDetails)upat.getPrincipal()).getUser();
        Set<Appointment> appointments = appointmentRepository.getPatientsPastAppointments(patient.getId());
        Set<AppointmentSurgeryDTO> retVal = new HashSet<>();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String type,doctor_name,patient_name,clinic_name,strDate;
        patient_name=patient.getFirstName() + " " + patient.getLastName();
        Long id,clinicId,doctorId;
        Integer clinicGrade,doctorGrade;
        for(Appointment appointment : appointments){
            type = appointment.getType().getType();
            doctor_name=appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName();
            doctorId=appointment.getDoctor().getId();
            clinic_name=appointment.getDoctor().getClinic().getClinicName();
            clinicId = appointment.getDoctor().getClinic().getId();
            ArrayList<Integer> grades = assignGrades(patient.getRatings(), doctorId, clinicId);
            doctorGrade = grades.get(0);
            clinicGrade = grades.get(1);
            strDate = dateFormat.format(appointment.getStartTime());
            id = appointment.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, patient_name,strDate,clinic_name,id,doctorId,
                    clinicId,null,clinicGrade,doctorGrade, null, null));
        }
        Set<Surgery> surgeries = surgeryRepository.getPatientsPastSurgeries(patient.getId());
        for(Surgery surgery : surgeries){
            type = "Surgery";
            Doctor doctor = surgery.getDoctors().iterator().next();
            doctor_name=doctor.getFirstName() + " " + doctor.getLastName();
            doctorId = doctor.getId();
            clinic_name=doctor.getClinic().getClinicName();
            clinicId = doctor.getClinic().getId();
            ArrayList<Integer> grades = assignGrades(patient.getRatings(), doctorId, clinicId);
            doctorGrade = grades.get(0);
            clinicGrade = grades.get(1);
            strDate = dateFormat.format(surgery.getStartTime());
            id = surgery.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, patient_name,strDate,clinic_name,id,doctorId,
                    clinicId,null,clinicGrade,doctorGrade, null, null));
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    private ArrayList<Integer> assignGrades(Set<Rating> ratings, Long doctorId, Long clinicId){
        Integer doctorGrade=0, clinicGrade=0;
        for(Rating rating : ratings){
            if(rating.getDoctorId()!=null&&rating.getDoctorId().compareTo(doctorId)==0){
                doctorGrade=rating.getRating();
            }
            if(rating.getClinicId()!=null&&rating.getClinicId().compareTo(clinicId)==0){
                clinicGrade=rating.getRating();
            }
        }
        ArrayList<Integer> retVal = new ArrayList<>();
        retVal.add(doctorGrade);retVal.add(clinicGrade);
        return retVal;
    }

    @GetMapping("/getFutureAppointmentsAndSurgeries")
    public ResponseEntity<Set<AppointmentSurgeryDTO>> getFutureAppointmentsAndSurgeries(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Patient patient = (Patient) ((MyUserDetails)upat.getPrincipal()).getUser();
        Set<AppointmentSurgeryDTO> retVal = new HashSet<>();
        // Maybe the patient will have to be taken from repository for consistency reasons
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String type,doctor_name,patient_name,clinic_name,strDate;
        Long id;
        for(Appointment appointment : patient.getFuture_appointments()){
            type = appointment.getType().getType();
            doctor_name=appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName();
            clinic_name=appointment.getDoctor().getClinic().getClinicName();
            strDate = dateFormat.format(appointment.getStartTime());
            id = appointment.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, null,strDate,clinic_name,id,null,
                    null,null,null,null, null, null));
        }
        for(Surgery surgery : patient.getSurgeries()){
            type = "Surgery";
            Doctor doctor = surgery.getDoctors().iterator().next();
            doctor_name=doctor.getFirstName() + " " + doctor.getLastName();
            clinic_name=doctor.getClinic().getClinicName();
            strDate = dateFormat.format(surgery.getStartTime());
            id = surgery.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, null,strDate,clinic_name,id,null,
                    null,null,null,null, null, null));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }
    /*NOT USED AT ALL*/
    @GetMapping("/getRatings")
    public ResponseEntity<Set<Rating>> getRatings(){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Patient patient = (Patient) ((MyUserDetails)upat.getPrincipal()).getUser();
        Set<Rating> retVal = patientRespository.findByEmail(patient.getEmail()).getRatings();
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @PostMapping("/rate")
    public ResponseEntity<String> rate(@RequestBody Rating rating){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Patient patient = (Patient) ((MyUserDetails)upat.getPrincipal()).getUser();
        if(rating.getDoctorId()==null&&rating.getClinicId()==null||
                (rating.getClinicId()!=null&&rating.getDoctorId()!=null)){
            return new ResponseEntity<>("Bad request body", HttpStatus.NOT_ACCEPTABLE);
        }if(rating.getRating()==null||rating.getRating()<1||rating.getRating()>10){
            return new ResponseEntity<>("Bad request body", HttpStatus.NOT_ACCEPTABLE);
        }
        new Thread(() -> {
            Patient finalPatient = patientRespository.findByEmail(patient.getEmail());
            finalPatient.rate(rating);
            patientRespository.save(finalPatient);
        }).start();
        return new ResponseEntity<>("",HttpStatus.OK);
    }

    @Transactional(readOnly = false, isolation = Isolation.SERIALIZABLE, propagation = Propagation.REQUIRED)
    @PostMapping("/bookFastAppointment/{id}")
    public ResponseEntity<String> bookFastAppointment(@PathVariable("id") String appId){
        if(!Authorized.isAuthorised(RoleEnum.PATIENT)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Long id;
        try {
            id = Long.parseLong(appId);
        } catch (NumberFormatException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        appointmentHandlingService.ReserveFastAppointment(id);
        return new ResponseEntity<>("",HttpStatus.OK);
    }
}
