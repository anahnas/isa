package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

@RestController
public class StaffController {
    private final UserRepository userRepository;

    public StaffController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @GetMapping("/getStaff")
    public ResponseEntity<Set<User>> getRequests(){
        //TODO aktivirati autorizaciju za ovu metodu
        /*if(!Authorized.isAuthorised(RoleEnum.CLINIC_CENTER_ADMIN)){
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }*/
        System.out.println("StaffController");
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
        retValue = userRepository.findStaff();
        return new ResponseEntity<>(retValue, HttpStatus.OK);
    }


}
