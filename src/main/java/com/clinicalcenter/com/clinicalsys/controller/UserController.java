package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.MyUserDetails;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationRequest;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationResponse;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.services.MyUserDetailsService;
import com.clinicalcenter.com.clinicalsys.util.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest auth_req) {
        System.out.println("usao");
        Authentication authentication;
        try {
            authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(auth_req.getUsername(), auth_req.getPassword())
            );
        }catch (UsernameNotFoundException e){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (BadCredentialsException e) {
            System.out.println("bad_credentials");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        }
        MyUserDetails myUserDetails = (MyUserDetails) myUserDetailsService
                .loadUserByUsername(auth_req.getUsername());
        String jwt = jwtUtil.generateToken(myUserDetails);
        return new ResponseEntity<>( new AuthenticationResponse(jwt), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println(user.getFirstName() + " " + user.getEmail());
        user.setAdminConfirmed(Boolean.FALSE);
        user.setActive(Boolean.FALSE);
        user.setRole(RoleEnum.PATIENT);
        userRepository.save(user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @Async
    @PostMapping("/confirm")
    public ResponseEntity<String> confirmAcc(@RequestBody String email){
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
}
