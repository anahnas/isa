package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping("/users/{email}/{password}")
    public User getUsers(@PathVariable("email") String email, @PathVariable("password") String password) {
        System.out.println("usaooo");
        User temp = userRepository.findByEmail(email);
        if(temp == null){
            System.out.println("User with email: " + email + " dose not exist!");
            return null;
        }
        if(!temp.getPassword().equals(password)){
            System.out.println("Wrong password");
            return null;
        }
        System.out.println(temp.getFirstName() + " " + temp.getPhoneNumber());
        return temp;
    }

    @PostMapping("/users")
    void addUser(@RequestBody User user) {
        System.out.println(user.getFirstName() + " " + user.getEmail());
        userRepository.save(user);
    }
}
