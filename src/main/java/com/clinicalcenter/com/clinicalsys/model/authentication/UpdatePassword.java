package com.clinicalcenter.com.clinicalsys.model.authentication;

public class UpdatePassword {
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    private String email;
    private String newpassword;


    public UpdatePassword(String email, String newpassword) {
        this.email = email;
        this.newpassword = newpassword;
    }

    public UpdatePassword(){}
}
