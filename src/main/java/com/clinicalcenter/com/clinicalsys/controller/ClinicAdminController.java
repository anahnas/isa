package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.Room;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentRepository;
import com.clinicalcenter.com.clinicalsys.repository.ClinicAdminRepository;
import com.clinicalcenter.com.clinicalsys.repository.DoctorRepository;
import com.clinicalcenter.com.clinicalsys.repository.RoomRepository;
import com.clinicalcenter.com.clinicalsys.services.NotifyUserSrvice;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.Set;

@RestController
@RequestMapping("/admin")
public class ClinicAdminController {

    @Autowired
    private NotifyUserSrvice notifyUserSrvice;

    @Autowired
    private ClinicAdminRepository clinicAdminRepository;
    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private DoctorRepository doctorRepository;

    public ClinicAdminController(ClinicAdminRepository clinicAdminRepository, AppointmentRepository appointmentRepository,
                                 RoomRepository roomRepository, NotifyUserSrvice notifyUserSrvice,
                                 DoctorRepository doctorRepository){
        this.clinicAdminRepository = clinicAdminRepository;
        this.appointmentRepository = appointmentRepository;
        this.roomRepository = roomRepository;
        this.notifyUserSrvice = notifyUserSrvice;
        this.doctorRepository = doctorRepository;
    }

    @GetMapping("/getAppointmentRequests/{email}")
    public ResponseEntity<Set<Appointment>> getAppointmentRequests(@PathVariable("email") String email){
        if(!Authorized.isAuthorised(email)){
            return new ResponseEntity<>(null,HttpStatus.FORBIDDEN);
        }
        Set<Appointment> appointments_to_process= clinicAdminRepository.findByEmail(email).getAppointments_to_process();
        return new ResponseEntity<>(appointments_to_process, HttpStatus.OK);
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
