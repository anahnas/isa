package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Set;

@RestController
@PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
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

    @PreAuthorize("hasRole('CLINIC_CENTER_ADMIN')")
    @GetMapping("/getrequests")
    public ResponseEntity<Set<User>> getRequests(){
        Set<User> retValue = userRepository.findRequests();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @Async
    @DeleteMapping("/deleterequest/{email}/{content}")
    public ResponseEntity<String> removeRequest(@PathVariable("email") String email, @PathVariable("content") String content) throws MailException, InterruptedException{
        User us = userRepository.findByEmail(email);
        if(us == null){
            System.out.println("User with email: " + email + " dose not exist!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {
            System.out.println("Deleting..." + email);
            System.out.println("Content: " + content);

            System.out.println("Sending mail...");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setFrom("spring.mail.username");
            mail.setSubject("Rejection");
            mail.setText("Explanation for registration rejection: \n" + content);
            this.mailSender.send(mail);
            System.out.println("Email sent..");
            userRepository.deleteByEmail(email);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

}