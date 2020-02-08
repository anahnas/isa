package com.clinicalcenter.com.clinicalsys.model.DTO;

public class AppTypeDTO {

    private Long id;

    private String type;

    public AppTypeDTO(Long id, String type) {
        this.id = id;
        this.type = type;
    }

    public AppTypeDTO() {
        //Empty Constructor needed for JSON conversion
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
