package com.clinicalcenter.com.clinicalsys.controller;


import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.DTO.AppointmentSurgeryDTO;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyAdminsServices;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/doctor")
public class DoctorController {

    private final UserRepository userRepository;
    private final RecipeRepository recipeRepository;
    private final DrugRepository drugRepository;
    private final PatientRepository patientRepository;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private NotifyAdminsServices notifyAdminsServices;

    public DoctorController(UserRepository userRepository, RecipeRepository recipeRepository,
                            DrugRepository drugRepository, PatientRepository patientRepository,
                            ClinicAdminRepository clinicAdminRepository, NotifyAdminsServices notifyAdminsServices,
                            SurgeryRepository surgeryRepository) {
        this.userRepository = userRepository;
        this.recipeRepository = recipeRepository;
        this.drugRepository = drugRepository;
        this.patientRepository = patientRepository;
        this.clinicAdminRepository = clinicAdminRepository;
        this.notifyAdminsServices = notifyAdminsServices;
        this.surgeryRepository = surgeryRepository;
    }

    @GetMapping("/getpatients")
    public ResponseEntity<Set<User>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<User> retValue = userRepository.allPatients();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @GetMapping("/getuser")
    public ResponseEntity<User> getUser(@RequestParam String email){

        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User u = userRepository.findByEmail(email);
        if(u != null){
            return new ResponseEntity<>(u, HttpStatus.OK);
        }
        else {
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping("/makerecipe/{email}")
    public ResponseEntity<String> makeRecipe(@RequestBody Drug drug, @PathVariable("email") String email){
        System.out.println("Usaooo->"+email);
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        else {
            Drug dr = drugRepository.findDrugByDrugName(drug.getDrugName());
            Recipe r = new Recipe();
            r.setDrug(dr);
            Patient p =((Patient)user);
            p.getMedicalRecord().getRecipes().add(r);
            patientRepository.save(p);
            return new ResponseEntity<>("", HttpStatus.OK);


        }
    }

    @GetMapping("/getFutureAppointmentsAndSurgeries")
    public ResponseEntity<Set<AppointmentSurgeryDTO>> getFutureAppointmentsAndSurgeries(){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor = (Doctor) ((MyUserDetails)upat.getPrincipal()).getUser();
        Set<AppointmentSurgeryDTO> retVal = new HashSet<>();
        // Maybe the patient will have to be taken from repository for consistency reasons
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        String type,patient_name,strDate;
        Long id, patientId;
        for(Appointment appointment : doctor.getAppointments()){
            if(appointment.getAppState()!= AppStateEnum.APPROVED){
                continue;
            }
            patient_name = appointment.getPatient().getFirstName() + " " + appointment.getPatient().getLastName();
            patientId = appointment.getPatient().getId();
            type = appointment.getType().getType();
            strDate = dateFormat.format(appointment.getStartTime());
            id = appointment.getId();
            retVal.add(new AppointmentSurgeryDTO(type, null, patient_name,strDate,null,id,null,
                    null,patientId, null,null,null,null,null));
        }
        for(Surgery surgery : doctor.getSurgeries()){
            type = "Surgery";
            patient_name = surgery.getPatient().getFirstName() + " " + surgery.getPatient().getLastName();
            patientId = surgery.getPatient().getId();
            strDate = dateFormat.format(surgery.getStartTime());
            id = surgery.getId();
            retVal.add(new AppointmentSurgeryDTO(type, null, patient_name,strDate,null,id,null,
                    null,patientId, null,null,null,null,null));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    @PostMapping("/requestSurgery/{date_start}/{date_end}")
    public ResponseEntity<String> requestSurgery(@RequestBody Patient patient,
                                                 @PathVariable("date_start") String date_start_string,
                                                 @PathVariable("date_end") String date_end_string){
        if(!Authorized.isAuthorised(RoleEnum.DOCTOR)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date_start,date_end;
        try {
            date_start = simpleDateFormat.parse(date_start_string);
            date_end = simpleDateFormat.parse(date_end_string);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>("Dates are not in the right format", HttpStatus.NOT_ACCEPTABLE);
        }
        Calendar start_cal = Calendar.getInstance();
        start_cal.setTime(date_start);
        start_cal.set(Calendar.MILLISECOND,0);
        Calendar end_cal = Calendar.getInstance();
        end_cal.setTime(date_end);
        end_cal.set(Calendar.MILLISECOND,0);
        if(start_cal.compareTo(end_cal)>=0){
            return new ResponseEntity<>("End has to be after start!",HttpStatus.NOT_ACCEPTABLE);
        }
        if(start_cal.get(Calendar.YEAR)!=end_cal.get(Calendar.YEAR)||
                (start_cal.get(Calendar.MONTH)!=end_cal.get(Calendar.MONTH))||
                (start_cal.get(Calendar.DATE)!=end_cal.get(Calendar.DATE))){
            return new ResponseEntity<>("Surgery must start and end on the same day!", HttpStatus.NOT_ACCEPTABLE);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Doctor doctor = (Doctor) ((MyUserDetails)upat.getPrincipal()).getUser();
        if(!doctor.checkIfAppFree(start_cal,end_cal)){
            return new ResponseEntity<>("You are not free at a given time, check your calender!",HttpStatus.NOT_ACCEPTABLE);
        }
        Surgery surgery = new Surgery(start_cal.getTime(),end_cal.getTime(),patient,doctor,null,null);
        surgery = surgeryRepository.save(surgery);
        Surgery finalSurgery = surgery;
        new Thread(() -> {
            Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(doctor.getEmail());
            for (ClinicAdmin admin:clinicAdmins){
                admin.getSurgeries_to_process().add(finalSurgery);
                admin = clinicAdminRepository.save(admin);
                notifyAdminsServices.newRequestNotification(admin,null);
            }
        }).start();
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
