package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.authentication.UpdatePassword;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.repository.ClinicAdminRepository;
import com.clinicalcenter.com.clinicalsys.repository.ClinicRespository;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import com.clinicalcenter.com.clinicalsys.util.Authorized;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@RestController
public class ClinicAdminController {
    private final UserRepository userRepository;
    private final ClinicRespository clinicRespository;
    private final ClinicAdminRepository caRep;

    public ClinicAdminController(UserRepository userRepository, ClinicRespository clinicRespository, ClinicAdminRepository caRep) {
        this.userRepository = userRepository;
        this.clinicRespository = clinicRespository;
        this.caRep = caRep;
    }

    @PostMapping("/regClinicAdmin/{clinic}")
    public ResponseEntity<String> addUser(@RequestBody User user, @PathVariable("clinic") String clinic) {
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        Clinic clc = clinicRespository.findByClinicName(clinic);
        System.out.println("Ovo je klinika koju treba obraditi: "+clinic);
        ClinicAdmin ca = new ClinicAdmin(user, clc);
        caRep.save(ca);
        return new ResponseEntity<>("", HttpStatus.OK);
    }

    @PostMapping("/cca/updatePassword")
    public ResponseEntity<String> updatePassword(@RequestBody UpdatePassword updatePassword){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User us = userRepository.findByEmail(updatePassword.getEmail());
        if(us == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {

            userRepository.updatePassword(updatePassword.getEmail(), updatePassword.getNewpassword());
            if(us.getFirstLogin() == true){
                userRepository.changeFirstLogin(updatePassword.getEmail());
            }
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @PostMapping("/cca/justUpdatePassword")
    public ResponseEntity<String> justUpdatePassword(@RequestBody UpdatePassword updatePassword){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        User us = userRepository.findByEmail(updatePassword.getEmail());
        if(us == null){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        else {

            userRepository.updatePassword(updatePassword.getEmail(), updatePassword.getNewpassword());
            return new ResponseEntity<>("", HttpStatus.OK);
        }
    }

    @GetMapping("/getadmins")
    public ResponseEntity<Set<User>> getRequests(){
        if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
        System.out.println("ClinicAdminController");
        Set<User> retValue = new Set<User>() {
            @Override
            public int size() {
                return 0;
            }

            @Override
            public boolean isEmpty() {
                return false;
            }

            @Override
            public boolean contains(Object o) {
                return false;
            }

            @Override
            public Iterator<User> iterator() {
                return null;
            }

            @Override
            public Object[] toArray() {
                return new Object[0];
            }

            @Override
            public <T> T[] toArray(T[] a) {
                return null;
            }

            @Override
            public boolean add(User user) {
                return false;
            }

            @Override
            public boolean remove(Object o) {
                return false;
            }

            @Override
            public boolean containsAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean addAll(Collection<? extends User> c) {
                return false;
            }

            @Override
            public boolean retainAll(Collection<?> c) {
                return false;
            }

            @Override
            public boolean removeAll(Collection<?> c) {
                return false;
            }

            @Override
            public void clear() {

            }
        };
        retValue = userRepository.findClinicAdmins();

        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }
}
