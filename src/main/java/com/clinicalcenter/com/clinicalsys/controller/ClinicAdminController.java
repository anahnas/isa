package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.DTO.AppointmentSurgeryDTO;
import com.clinicalcenter.com.clinicalsys.model.DTO.Doctor_FreeTimes;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyUserSrvice;
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

@RestController
@RequestMapping("/admin")
public class ClinicAdminController {

    @Autowired
    private NotifyUserSrvice notifyUserSrvice;
    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private ClinicRespository clinicRespository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private SurgeryRepository surgeryRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DoctorRepository doctorRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private VacationRepository vacationRepository;

    public ClinicAdminController(ClinicAdminRepository clinicAdminRepository, AppointmentRepository appointmentRepository,
                                 RoomRepository roomRepository, NotifyUserSrvice notifyUserSrvice,
                                 DoctorRepository doctorRepository, ClinicRespository clinicRespository,
                                 SurgeryRepository surgeryRepository, VacationRepository vacationRepository){
        this.clinicAdminRepository = clinicAdminRepository;
        this.appointmentRepository = appointmentRepository;
        this.roomRepository = roomRepository;
        this.notifyUserSrvice = notifyUserSrvice;
        this.doctorRepository = doctorRepository;
        this.clinicRespository = clinicRespository;
        this.surgeryRepository = surgeryRepository;
        this.vacationRepository = vacationRepository;
    }

    @GetMapping("/getAppointmentAndSurgeryRequests")
    public ResponseEntity<Set<AppointmentSurgeryDTO>> getAppointmentRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        ClinicAdmin admin = (ClinicAdmin) ((MyUserDetails)upat.getPrincipal()).getUser();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Set<AppointmentSurgeryDTO> retVal = new HashSet<AppointmentSurgeryDTO>();
        Set<Appointment> appointments_to_process= admin.getAppointments_to_process();
        String type,doctor_name,patient_name,clinic_name,strDate;
        Long id,clinicId,doctorId,patientId;
        Integer clinicGrade,doctorGrade;
        for(Appointment appointment : appointments_to_process){
            type = appointment.getType().getType();
            doctor_name=appointment.getDoctor().getFirstName() + " " + appointment.getDoctor().getLastName();
            doctorId=appointment.getDoctor().getId();
            patient_name=appointment.getPatient().getFirstName() + " " + appointment.getDoctor().getLastName();
            patientId=appointment.getPatient().getId();
            strDate = dateFormat.format(appointment.getStartTime());
            id = appointment.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, patient_name,strDate,null,id,doctorId,
                    null,patientId,null,null, null, null,null));
        }
        Set<Surgery> surgeries_to_process= admin.getSurgeries_to_process();
        for(Surgery surgery : surgeries_to_process){
            type = "Surgery";
            Doctor doctor = surgery.getDoctors().iterator().next();
            doctor_name=doctor.getFirstName() + " " + doctor.getLastName();
            doctorId = doctor.getId();
            patient_name=surgery.getPatient().getFirstName() + " " + surgery.getPatient().getLastName();
            patientId=surgery.getPatient().getId();
            strDate = dateFormat.format(surgery.getStartTime());
            id = surgery.getId();
            retVal.add(new AppointmentSurgeryDTO(type, doctor_name, patient_name,strDate,null,id,doctorId,
                    null,patientId,null,null, null, null, null));
        }
        return new ResponseEntity<>(retVal, HttpStatus.OK);
    }

    /*Return free rooms for appointment OR surgery, depending on type variable*/
    @GetMapping("/getFreeRoomsForAppointment/{appointment_id}/{type}")
    public ResponseEntity<Set<Room>> getFreeRoomsForDate(@PathVariable("appointment_id") String AppId,
                                                         @PathVariable("type") String type){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Long app_id;
        try {
            app_id = Long.parseLong(AppId);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        Appointment apt= appointmentRepository.findByIdMy(app_id);
        if(apt==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        Set<Room> rooms = apt.getDoctor().getClinic().getRooms();
        Set<Room> available_rooms = new HashSet<>();
        for(Room room : rooms){
            if (room.isFree(apt.getStartTime(),apt.getEndTime())){
                if(room.getType()== RoomType.valueOf(type))
                    available_rooms.add(room);
            }
        }
        return new ResponseEntity<>(available_rooms, HttpStatus.OK);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    @GetMapping("/assignRoomToAppointment/{roomId}/{appId}")
    public ResponseEntity<String> assignRoomToAppointment(@PathVariable("roomId") String roomId,
                                                          @PathVariable("appId") String appId){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Long app_id,room_id;
        try{
            app_id = Long.parseLong(appId);
            room_id = Long.parseLong(roomId);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        Room room = roomRepository.findByIdMy(room_id);
        if(room==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
            Appointment app=appointmentRepository.findByIdMy(app_id);
            Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(app.getDoctor().getEmail());
            for (ClinicAdmin admin:clinicAdmins){
                admin.getAppointments_to_process().remove(app);
                admin = clinicAdminRepository.save(admin);
            }
            app.setAppState(AppStateEnum.APPROVED);
            app.getDoctor().getAppointments().add(app);
            doctorRepository.save(app.getDoctor());
            app.addRoom(room);
            app = appointmentRepository.save(app);
            room.addAppointment(app);
            roomRepository.save(room);
            Appointment finalApp = app;
            new Thread(()-> {
                notifyUserSrvice.AppointmentAnswer(finalApp.getPatient(),true);
            }).start();
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    @GetMapping("/assignRoomToSurgery/{roomId}/{surgId}")
    public ResponseEntity<String> assignRoomToSurgery(@PathVariable("roomId") String roomId,
                                                          @PathVariable("surgId") String surgId){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Long surg_id,room_id;
        try{
            surg_id = Long.parseLong(surgId);
            room_id = Long.parseLong(roomId);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        Room room = roomRepository.findByIdMy(room_id);
        if(room==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        };
        Surgery surg=surgeryRepository.findByIdMy(surg_id);
        Set<Doctor> doctors_set = surg.getDoctors();
        ArrayList<Doctor> doctors = new ArrayList<>(doctors_set);
        Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(doctors.get(0).getEmail());
        for (ClinicAdmin admin:clinicAdmins){
            admin.getSurgeries_to_process().remove(surg);
            admin = clinicAdminRepository.save(admin);
        }
        surg.setAppState(AppStateEnum.APPROVED);
        for(Doctor doctor:doctors) {
            doctor.getSurgeries().add(surg);
            doctorRepository.save(doctor);
        }
        surg.setRoom(room);
        surg = surgeryRepository.save(surg);
        room.getFuture_surgeries().add(surg);
        roomRepository.save(room);
        Surgery finalSurg = surg;
        new Thread(()-> {
            notifyUserSrvice.AppointmentAnswer(finalSurg.getPatient(),true);
        }).start();

        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping("/rejectAppointment/{appId}")
    public ResponseEntity<String> rejectAppointment(@PathVariable("appId") String appId){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Long app_id;
        try{
            app_id = Long.parseLong(appId);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
            Appointment app=appointmentRepository.findByIdMy(app_id);
            Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(app.getDoctor().getEmail());
            for (ClinicAdmin admin:clinicAdmins){
                admin.getAppointments_to_process().remove(app);
                admin = clinicAdminRepository.save(admin);
            }
            app.setAppState(AppStateEnum.REJECTED);
            appointmentRepository.save(app);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping("/rejectSurgery/{surgId}")
    public ResponseEntity<String> rejectSurgery(@PathVariable("surgId") String surgId){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Long surg_id;
        try{
            surg_id = Long.parseLong(surgId);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
            Surgery surg=surgeryRepository.findByIdMy(surg_id);
            if(surg==null){
                return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
            }
            Set<Doctor> doctors_set = surg.getDoctors();
            ArrayList<Doctor> doctors = new ArrayList<>(doctors_set);
            Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(doctors.get(0).getEmail());
            for (ClinicAdmin admin:clinicAdmins){
                admin.getSurgeries_to_process().remove(surg);
                admin = clinicAdminRepository.save(admin);
            }
            surg.setAppState(AppStateEnum.REJECTED);
            surgeryRepository.save(surg);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    /*Returns Doctors that are specialized in given AppointmentType, work for that clinic and are free at defined time*/
    @PostMapping("/getAvailablDoctors/{date}/{email}")
    public ResponseEntity<Set<Doctor>> getFreeSpecializedDoctors(@RequestBody AppointmentType appType,
                                                                           @PathVariable("date") String date_string,
                                                                           @PathVariable("email") String email) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        User u = userRepository.findByEmail(email);
        Clinic clinic = clinicRespository.findByClinicName(((ClinicAdmin)u).getClinic().getClinicName());
        Set<Doctor> doctors_temp= new HashSet<Doctor>(doctorRepository.allWithSpecializationInClinic(appType.getId(),clinic.getId()));
        Set<Doctor> retVal=new HashSet<>();
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.SECOND,0);
        start.set(Calendar.MILLISECOND,0);
        Calendar end = (Calendar) start.clone();
        end.add(Calendar.MINUTE, 30);
        for(Doctor doctor : doctors_temp){
            if(doctor.checkIfAppFree(start,end))
                retVal.add(doctor);
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    /*Returns Doctors that are specialized in given AppointmentType, work for that clinic and are free at defined time*/
    @GetMapping("/getAvailablRooms/{date}/{email}")
    public ResponseEntity<Set<Room>> getFreeSpecializedDoctors(  @PathVariable("date") String date_string,
                                                                 @PathVariable("email") String email) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(date_string);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        User u = userRepository.findByEmail(email);
        Clinic clinic = clinicRespository.findByClinicName(((ClinicAdmin)u).getClinic().getClinicName());
        Set<Room> rooms= clinic.getRooms();
        Set<Room> retVal=new HashSet<>();
        Calendar start = Calendar.getInstance();
        start.setTime(date);
        start.set(Calendar.SECOND,0);
        start.set(Calendar.MILLISECOND,0);
        Calendar end = (Calendar) start.clone();
        end.add(Calendar.MINUTE, 30);
        for(Room room : rooms){
            if(room.isFree(start.getTime(),end.getTime()))
                retVal.add(room);
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @GetMapping("getClinicAppointmentTypes/{email}")
    public ResponseEntity<Set<AppointmentType>> getClinicAppointmentTypes(@PathVariable("email") String email){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmail(email);
        Set<AppointmentType_Price_Discount> atpd = clinicAdmin.getClinic().getAppointmentTypePriceDiscounts();
        Set<AppointmentType> retVal = new HashSet<>();
        for (AppointmentType_Price_Discount i : atpd){
            retVal.add(i.getAppointmentType());
        }
        return new ResponseEntity<>(retVal,HttpStatus.OK);
    }

    @PostMapping("/addPredefinedAppointment/{email}")
    public ResponseEntity<String> predefinedAppointment(@RequestBody Appointment app,
                                                        @PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        ClinicAdmin clinicAdmin = clinicAdminRepository.findByEmail(email);
        app.setAppState(AppStateEnum.APPROVED);
        Calendar end = Calendar.getInstance();
        end.setTime(app.getStartTime());
        end.add(Calendar.MINUTE,30);
        app.setEndTime(end.getTime());
        app = appointmentRepository.save(app);
        clinicAdmin.getClinic().getPredefinedAppointments().add(app);
        clinicRespository.save(clinicAdmin.getClinic());
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
    @GetMapping("/getVacationRequests/{email}")
    public ResponseEntity<Set<VacationRequest>> getVacationRequests(@PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Set<VacationRequest> vacations_to_process= clinicAdminRepository.findByEmail(email).getVacations_to_process();
        return new ResponseEntity<>(vacations_to_process, HttpStatus.OK);
    }

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    @PostMapping("/respondToVacationRequest/{vacation_id}")
    public ResponseEntity<String> acceptVacation(@RequestBody Boolean status,
                                                               @PathVariable("vacation_id") String vacation_id){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        ClinicAdmin admin = (ClinicAdmin) ((MyUserDetails)upat.getPrincipal()).getUser();
        Long vacId;
        try{
            vacId = Long.parseLong(vacation_id);
        }catch (Exception e){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        if(!vacationRepository.existsById(vacId)){
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        if(status){
            vacationRepository.acceptRequest(vacId);
        }else{
            vacationRepository.denyRequest(vacId);
    }
        admin.getVacations_to_process().remove(vacationRepository.findByIdMy(vacId));
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}
