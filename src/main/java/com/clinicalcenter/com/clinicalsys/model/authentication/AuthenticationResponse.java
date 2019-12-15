package com.clinicalcenter.com.clinicalsys.model.authentication;

public class AuthenticationResponse {

    private String token;
    private String role;
    private String firstName;
    private String secondName;

    public AuthenticationResponse(String token, String role, String firstName, String secondName){
        this.token = token;
        this.role = role;
        this.firstName = firstName;
        this.secondName = secondName;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getToken(){
        return  token;
    }

    public String getRole() {
        return role;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSecondName() {
        return secondName;
    }
}
