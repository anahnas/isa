package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{email}/{password}")
    public ResponseEntity<User> getUsers(@PathVariable("email") String email, @PathVariable("password") String password) {
        System.out.println("usaooo");
        User temp = userRepository.findByEmail(email);
        if(temp == null){
            System.out.println("User with email: " + email + " dose not exist!");
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        if(!temp.getPassword().equals(password)){
            System.out.println("Wrong password");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        System.out.println(temp.getFirstName() + " " + temp.getPhoneNumber());
        return new ResponseEntity<>(temp, HttpStatus.OK);
    }

    @PostMapping("/users")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        user.setRole(RoleEnum.PATIENT);
        System.out.println(user.getFirstName() + " " + user.getEmail());
        // TODO validacija
        userRepository.save(user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }
}
