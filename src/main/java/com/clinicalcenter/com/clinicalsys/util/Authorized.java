package com.clinicalcenter.com.clinicalsys.util;

import com.clinicalcenter.com.clinicalsys.model.MyUserDetails;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;

public class Authorized {

    //Check the Role of the logged user
    public static boolean isAuthorised(RoleEnum role){
        String user_role = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().toArray()[0].toString();
        if (user_role.equals(role.toString()))
            return true;
        return false;
    }

    //Check the email(username) of logged user
    public static boolean isAuthorised(String email){
        if(!SecurityContextHolder.getContext().getAuthentication().isAuthenticated()){
            return false;
        }
        if(SecurityContextHolder.getContext().getAuthentication() instanceof AnonymousAuthenticationToken){
            return false;
        }
        UsernamePasswordAuthenticationToken upat = (UsernamePasswordAuthenticationToken)
                SecurityContextHolder.getContext().getAuthentication();
        if(((MyUserDetails)upat.getPrincipal()).getUser().getEmail().equals(email))
            return true;
        return false;
    }
}
