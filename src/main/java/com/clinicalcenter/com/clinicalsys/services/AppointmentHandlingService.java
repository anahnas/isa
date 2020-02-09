package com.clinicalcenter.com.clinicalsys.services;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.MyUserDetails;
import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentRepository;
import com.clinicalcenter.com.clinicalsys.repository.AppointmentTypeRepository;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AppointmentHandlingService {

    @Autowired
    AppointmentRepository appointmentRepository;
    @Autowired
    PatientRepository patientRepository;

    @Transactional(readOnly = false, propagation = Propagation.REQUIRES_NEW, isolation = Isolation.READ_COMMITTED)
    public ResponseEntity<String> ReserveFastAppointment(Long appointmentId) {
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        Patient patient = (Patient) ((MyUserDetails) upat.getPrincipal()).getUser();

        Appointment a = appointmentRepository.findByIdMy(appointmentId);
        if (a.getAppState() != AppStateEnum.APPROVED) {
            return new ResponseEntity<>("Fast Appointment already booked", HttpStatus.NOT_ACCEPTABLE);
        }
        appointmentRepository.bookFastApp(patient.getId(), appointmentId);
        //deletes appointment form clinic's predefined appointments list
        appointmentRepository.deletePredefinedAppointment(appointmentId);
        patient.getFuture_appointments().add(a);
        patientRepository.save(patient);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
