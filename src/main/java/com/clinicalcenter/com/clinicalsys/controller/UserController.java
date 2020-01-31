package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.MyUserDetails;
import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationRequest;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationResponse;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.services.MyUserDetailsService;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
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
    private PatientRepository patientRepository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    private JwtUtil jwtUtil;

    public UserController(UserRepository userRepository, PatientRepository patientRepository) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthenticationResponse> login(@RequestBody AuthenticationRequest auth_req) {
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
        MyUserDetails myUD = (MyUserDetails) myUserDetailsService
                .loadUserByUsername(auth_req.getUsername());
        String jwt = jwtUtil.generateToken(myUD);
        return new ResponseEntity<>(
                new AuthenticationResponse(jwt, myUD.getUser()), HttpStatus.OK);
    }

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@RequestBody User user) {
        System.out.println(user.getFirstName() + " " + user.getEmail());
        user.setAdminConfirmed(Boolean.FALSE);
        user.setActive(Boolean.FALSE);
        user.setRole(RoleEnum.PATIENT);
        Patient patient = new Patient(user);
        patientRepository.save(patient);
        //TODO backgroud checking for same email
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/edit")
    public  ResponseEntity<User> editInfo(@RequestBody User u){
        if(!Authorized.isAuthorised(u.getEmail())){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        patientRepository.editPatient(u.getFirstName(),u.getLastName(),u.getPassword(),u.getAddress(),
                u.getCity(),u.getPhoneNumber(),u.getCountry(),u.getEmail());
        return new ResponseEntity<>(u, HttpStatus.OK);
    }

    @PostMapping("/newcca")
    public ResponseEntity<String> newCCA(@RequestBody User user){

        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        user.setAdminConfirmed(Boolean.TRUE);
        user.setActive(Boolean.TRUE);
        user.setRole(RoleEnum.CLINIC_CENTER_ADMIN);
        userRepository.save(user);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/confirm")
    public ResponseEntity<String> confirmAcc(@RequestBody String email){
        Patient p = patientRepository.findByEmail(email);
        if(p == null){
            return new ResponseEntity<>("Something gone wrong", HttpStatus.BAD_REQUEST);
        }
        else if(p.getActive()){
            return new ResponseEntity<>("User is already active", HttpStatus.NO_CONTENT);
        }
        patientRepository.setActive(email);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/getuser")
    public ResponseEntity<User> getUser(@RequestBody String email){
        User u = userRepository.findByEmail(email);
        if(u == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else if(u.getActive()){
            return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(u, HttpStatus.OK);
    }
}
