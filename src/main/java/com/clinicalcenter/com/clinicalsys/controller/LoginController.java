package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.dto.UserDTO;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class LoginController {
    @Autowired
    private UserService userService;


    @RequestMapping(method = RequestMethod.POST, value = "/logUser")
    @ResponseBody
    public ResponseEntity<?> findByEmail (@Valid @RequestBody UserDTO user){
        String mess =  userService.checkLogin(user);
        if (!mess.equals("true")) {
            return new ResponseEntity<>(mess, HttpStatus.BAD_REQUEST);
        } else {
            return new ResponseEntity<String>("true", HttpStatus.OK);
        }
    }
}
