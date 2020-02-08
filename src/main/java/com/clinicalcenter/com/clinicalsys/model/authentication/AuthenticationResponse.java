package com.clinicalcenter.com.clinicalsys.model.authentication;

import com.clinicalcenter.com.clinicalsys.model.User;

public class AuthenticationResponse {

    private String token;
    private User user;

    public AuthenticationResponse() {
    }

    public AuthenticationResponse(String token, User user) {
        this.token = token;
        this.user = user;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
