package com.clinicalcenter.com.clinicalsys.services;

import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotifyAdminsServis {

    @Autowired
    private JavaMailSender mailSender;

    public NotifyAdminsServis (JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }
    public void newAppointmentRequestNotification(ClinicAdmin ca){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(ca.getEmail());
        mail.setFrom("spring.mail.username");
        mail.setSubject("New Appointment Request");
        mail.setText("New request for appointment was received. To see more details, accept or reject it, go to " +
                "the site and check Appointment Requests section.");
        new Thread(() -> {
            this.mailSender.send(mail);
        }).start();
    }
}
