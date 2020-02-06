package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.authentication.DeleteRequest;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Set;

@RestController
@RequestMapping("/admin")
public class RequestsController {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public RequestsController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @GetMapping("/getrequests")
    public ResponseEntity<Set<User>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Set<User> retValue = userRepository.findRequests();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @Async
    @PostMapping("/acceptrequest")
    //@Transactional(readOnly = true, isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<String> acceptRequest(@RequestBody String email) throws MailException{
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User user = userRepository.findByEmail(email);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        patientRepository.setConfirmed(user.getEmail());

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("spring.mail.username");
        mail.setSubject("Confirmation");
        mail.setText("Please confirm your registration by click on link bellow: \n\n" + "http://localhost:4200/accept/"+email);
        new Thread(() -> {
            this.mailSender.send(mail);
        }).start();
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Async
    @PostMapping("/deleterequest")
    //@Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED)
    public ResponseEntity<String> removeRequest(@RequestBody DeleteRequest rqst)
            throws MailException, InterruptedException{
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User us = userRepository.findByEmail(rqst.getEmail());
        if(us == null){
            System.out.println("User with email: " + rqst.getEmail() + " dose not exist!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(rqst.getEmail());
            mail.setFrom("spring.mail.username");
            mail.setSubject("Rejection");
            mail.setText("Explanation for registration rejection: \n" + rqst.getContent());
            new Thread(() -> {
                this.mailSender.send(mail);
            }).start();
            System.out.println("Email sent..");
            userRepository.deleteByEmail(rqst.getEmail());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }



}
