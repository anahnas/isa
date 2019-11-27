package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class ReuqestsController {

    private final UserRepository userRepository;

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
}
