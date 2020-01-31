package com.clinicalcenter.com.clinicalsys.model;

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

    @Column(name = "startDate", unique = false, nullable = false)
    private Date startDate;

    @Column(name = "endDate", unique = false, nullable = false)
    private Date endDate;

    @ManyToOne(fetch = FetchType.EAGER)
    private Staff staff;

    public VacationRequest() {
    }
}
