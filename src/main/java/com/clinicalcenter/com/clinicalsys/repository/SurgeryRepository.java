package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Surgery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface SurgeryRepository extends JpaRepository<Surgery,Long> {

    @Query(value = "Select s from Surgery s where s.id = ?1")
    Surgery findByIdMy(Long app_surg_id);

    @Query(value = "SELECT s from Surgery s where s.patient.id=?1 and s.appState=4")
    Set<Surgery> getPatientsPastSurgeries(Long id);
}
