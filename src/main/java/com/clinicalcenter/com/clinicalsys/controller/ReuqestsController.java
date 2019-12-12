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
import org.springframework.web.bind.annotation.*;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReuqestsController {

    private final UserRepository userRepository;
    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private Environment env;

    public ReuqestsController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/getrequests")
    public ResponseEntity<Set<User>> getRequests(){
        Set<User> retValue = userRepository.findRequests();
        if(retValue==null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }

    @Async
    @PutMapping("/acceptrequest/{email}")
    public ResponseEntity<String> acceptRequest(@PathVariable("email") String email) throws MailException{
        User user = userRepository.findByEmail(email);
        if(user == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        user.setAdminConfirmed(Boolean.TRUE);
        userRepository.save(user);

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo(email);
        mail.setFrom("spring.mail.username");
        mail.setSubject("Confirmation");
        mail.setText("Please confirm your registration by click on link bellow: \n\n" + "http://localhost:4200/accept/"+email);
        this.mailSender.send(mail);

        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Async
    @PutMapping("/confirm/{email}")
    public ResponseEntity<String> confirmAcc(@PathVariable("email") String email){
        User u = userRepository.findByEmail(email);
        if(u == null){
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
        else if(u.getActive()){
            return new ResponseEntity<>("User is already active", HttpStatus.NO_CONTENT);
        }
        u.setActive(Boolean.TRUE);
        userRepository.save(u);
        return new ResponseEntity<>("", HttpStatus.OK);
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

            System.out.println("Slanje emaila...");
            SimpleMailMessage mail = new SimpleMailMessage();
            mail.setTo(email);
            mail.setFrom("spring.mail.username");
            mail.setSubject("Rejection");
            mail.setText("Explanation for reqistration rejection: \n" + content);
            this.mailSender.send(mail);
            System.out.println("Email sent..");
            userRepository.deleteByEmail(email);
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }



}