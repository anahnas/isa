package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyUserSrvice;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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
    private RoomRepository roomRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public ClinicAdminController(ClinicAdminRepository clinicAdminRepository, AppointmentRepository appointmentRepository,
                                 RoomRepository roomRepository, NotifyUserSrvice notifyUserSrvice,
                                 DoctorRepository doctorRepository, ClinicRespository clinicRespository){
        this.clinicAdminRepository = clinicAdminRepository;
        this.appointmentRepository = appointmentRepository;
        this.roomRepository = roomRepository;
        this.notifyUserSrvice = notifyUserSrvice;
        this.doctorRepository = doctorRepository;
        this.clinicRespository = clinicRespository;
    }

    @GetMapping("/getAppointmentRequests/{email}")
    public ResponseEntity<Set<Appointment>> getAppointmentRequests(@PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Set<Appointment> appointments_to_process= clinicAdminRepository.findByEmail(email).getAppointments_to_process();
        return new ResponseEntity<>(appointments_to_process, HttpStatus.OK);
    }

    @GetMapping("/getFreeRoomsForAppointment/{appointment_id}")
    public ResponseEntity<Set<Room>> getFreeRoomsForDate(@PathVariable("appointment_id") String AppId){
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
                if(room.getType()== RoomType.EXAMINATION)
                    available_rooms.add(room);
            }
        }
        return new ResponseEntity<>(available_rooms, HttpStatus.OK);
    }

    @GetMapping("/assignRoomToAppointment/{roomId}/{appId}")
    public ResponseEntity<String> assignRoomToAppointment(@PathVariable("roomId") String roomId,
                                                          @PathVariable("appId") String appId){
        Long app_id,room_id;
        try{
            app_id = Long.parseLong(appId);
            room_id = Long.parseLong(roomId);
        }catch (Exception e){
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
        Room room = roomRepository.findByIdMy(room_id);
        app.addRoom(room);
        app = appointmentRepository.save(app);
        room.addAppointment(app);
        roomRepository.save(room);
        notifyUserSrvice.AppointmentAnswer(app.getPatient(),true);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }

    @PostMapping("/rejectAppointment/{appId}")
    public ResponseEntity<String> rejectAppointment(@PathVariable("appId") String appId){
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

    @GetMapping("/getVacationRequests/{email}")
    public ResponseEntity<Set<VacationRequest>> getVacationRequests(@PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Set<VacationRequest> vacations_to_process= clinicAdminRepository.findByEmail(email).getVacations_to_process();
        return new ResponseEntity<>(vacations_to_process, HttpStatus.OK);
    }
}
