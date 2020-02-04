package com.clinicalcenter.com.clinicalsys.services;

import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import com.clinicalcenter.com.clinicalsys.model.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class NotifyUserSrvice {

    @Autowired
    private JavaMailSender mailSender;

    public NotifyUserSrvice (JavaMailSender javaMailSender){
        this.mailSender = javaMailSender;
    }

    public void AppointmentAnswer(Patient p,boolean accepted){
        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(p.getEmail());
        mail.setFrom("spring.mail.username");
        if(accepted) {
            mail.setSubject("Appointment Approved");
            mail.setText("Your appointment request has been approved. To see more details go to the web app.");
        }else{
            mail.setSubject("Appointment Rejected");
            mail.setText("Your appointment request has been rejected.");
        }
        new Thread(() -> {
            this.mailSender.send(mail);
        }).start();
    }
}
