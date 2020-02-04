package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.Room;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyUserSrvice;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
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

    @GetMapping("/getFreeRoomsForTime/{date}/{clinicName}")
    public ResponseEntity<Set<Room>> getFreeRoomsForDate(@PathVariable("date") String datestr,
                                                         @PathVariable("clinicName") String clinicName){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_ADMIN)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        String pattern = "yyyy-MM-dd HH:mm:ss";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        Date date;
        try {
            date = simpleDateFormat.parse(datestr);
        } catch (ParseException e) {
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }

        Clinic clinic = clinicRespository.findByClinicName(clinicName);
        if(clinic==null){
            return new ResponseEntity<>(null,HttpStatus.NOT_ACCEPTABLE);
        }
        Date end_date;
        Calendar temp = Calendar.getInstance();
        temp.setTime(date);
        temp.add(Calendar.MINUTE,30);
        end_date = temp.getTime();

        Set<Room> rooms = clinic.getRooms();
        Set<Room> available_rooms = new HashSet<>();
        for(Room room : rooms){
            if (room.isFree(date,end_date)){
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
        app.setAppState(AppStateEnum.APPROVED);
        app.getDoctor().getAppointments().add(app);
        doctorRepository.save(app.getDoctor());
        Room room = roomRepository.findByIdMy(room_id);
        app.addRoom(room);
        app = appointmentRepository.save(app);
        room.getFuture_appointments().add(app);
        roomRepository.save(room);
        notifyUserSrvice.AppointmentAnswer(app.getPatient(),true);
        return new ResponseEntity<>(null,HttpStatus.OK);
    }
}
