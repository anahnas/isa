package com.clinicalcenter.com.clinicalsys.model.authentication;

public class AuthenticationResponse {

    private String token;

    public AuthenticationResponse(String token){
        this.token = token;
    }

    public String getToken(){
        return  token;
    }
}
