package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Set;

@RestController
@RequestMapping("/admin")
public class RequestsController {

    private final UserRepository userRepository;

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public RequestsController(UserRepository userRepository) {
        this.userRepository = userRepository;
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
    @DeleteMapping("/deleterequest/{email}/{content}")
    public ResponseEntity<String> removeRequest(@PathVariable("email") String email, @PathVariable("content") String content)
            throws MailException, InterruptedException{
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User us = userRepository.findByEmail(email);
        if(us == null){
            System.out.println("User with email: " + email + " dose not exist!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setFrom("spring.mail.username");
            mail.setSubject("Rejection");
            mail.setText("Explanation for registration rejection: \n" + content);
            this.mailSender.send(mail);
            userRepository.deleteByEmail(email);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

}