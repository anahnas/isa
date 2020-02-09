package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Clinic;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface ClinicRespository extends JpaRepository<Clinic, Long> {

    //Clinic findByName(String clinicName);
    @Transactional
    Clinic findByAddress(String address);

    @Query("SELECT c FROM Clinic c ")
    Set<Clinic> findClinics();

    @Transactional
    Clinic findByClinicName(String clinicName);

    @Query( value = "select c from Clinic c where c.id=?1")
    Clinic findByIdMy(Long parseLong);
}
