package com.clinicalcenter.com.clinicalsys.services;

import com.clinicalcenter.com.clinicalsys.dto.UserDTO;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;


    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }


    public String checkReg(User user) {
        if (user.getFirstName().isEmpty()||user.getPassword().isEmpty()||user.getEmail().isEmpty()|| user.getAddress().isEmpty()||user.getLastName().isEmpty()){
            return "All fields are required!";
        }


        if (findByEmail(user.getEmail()) != null){
            return "Email already taken!";
        }

        User u = repository.save(user);
        return "true";
    }

    public String checkLogin(UserDTO user) {
        if (user.getEmail().isEmpty()||user.getPassword().isEmpty()){
            return "All fields are required!";
        }
        List<User> all = repository.findAll();

        for(User u : all){
            if(u.getEmail().equals(user.getEmail()) && u.getPassword().equals(user.getPassword()))
                return "true";
        }

        return "Wrong credentials.";
    }
}
