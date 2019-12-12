package com.clinicalcenter.com.clinicalsys.util;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import org.springframework.security.core.context.SecurityContextHolder;

public class Authorized {

    public static boolean isAuthorised(RoleEnum role){
        String user_role = SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().toArray()[0].toString();
        if (user_role.equals(role.toString()))
            return true;
        return false;
    }
}
