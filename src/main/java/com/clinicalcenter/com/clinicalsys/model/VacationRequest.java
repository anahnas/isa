package com.clinicalcenter.com.clinicalsys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Setter
@Getter
@Entity
@Table(name = "vacationReq")
public class VacationRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", unique = false, nullable = false)
    private String type;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    private Staff staff ;

    @Column(name = "startDate", unique = false, nullable = false)
    private Date startDate;

    @Column(name = "endDate", unique = false, nullable = false)
    private Date endDate;

    @Column(name = "accepted")
    private Boolean accepted;

    public VacationRequest() {
    }

    public VacationRequest(String type, Date startDate, Date endDate, Boolean accepted) {
        this.type = type;
        this.startDate = startDate;
        this.endDate = endDate;
        this.accepted = accepted;
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

    public Staff getStaff() {
        return staff;
    }

    public void setStaff(Staff staff) {
        this.staff = staff;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public Boolean getAccepted() {
        return accepted;
    }

    public void setAccepted(Boolean accepted) {
        this.accepted = accepted;
    }
}
