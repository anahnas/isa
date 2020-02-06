package com.clinicalcenter.com.clinicalsys.services;

import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotifyAdminsServices {

    @Autowired
    private JavaMailSender mailSender;

    public NotifyAdminsServices(JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }
    public void newRequestNotification(ClinicAdmin ca,Boolean appointment){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(ca.getEmail());
        mail.setFrom("spring.mail.username");
        if(appointment==null){
            mail.setSubject("New Surgery Request");
            mail.setText("New request for surgery was created. To see more details, accept or reject it, go to " +
                    "the site and check Surgery Requests section.");
        }else if(appointment==Boolean.TRUE) {
            mail.setSubject("New Appointment Request");
            mail.setText("New request for appointment was received. To see more details, accept or reject it, go to " +
                    "the site and check Appointment Requests section.");
        }else{
            mail.setSubject("New Vacation Request");
            mail.setText("New request for vacation was received. To see more details, accept or reject it, go to " +
                    "the site and check Vacation Requests section.");
        }
        this.mailSender.send(mail);
    }
}
