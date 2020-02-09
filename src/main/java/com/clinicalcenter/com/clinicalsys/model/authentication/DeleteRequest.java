package com.clinicalcenter.com.clinicalsys.model.authentication;

import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;

public class DeleteRequest {

    private String email;
    private String content;
    private Long Id;
    private String name;


    public DeleteRequest() {

    }

    public DeleteRequest(String email, String content) {
        this.email = email;
        this.content = content;
    }
    public DeleteRequest(Long Id, String content) {
        this.Id = Id;
        this.content = content;
    }


    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }
}
