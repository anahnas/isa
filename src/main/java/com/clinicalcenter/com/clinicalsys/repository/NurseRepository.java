package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Nurse;
import com.clinicalcenter.com.clinicalsys.model.Recipe;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {

    /*find nurse by clinic she works for*/
    @Query(value = "SELECT * FROM clinicalsys.user INNER JOIN staff on clinicalsys.user.id=staff.id " +
            "INNER JOIN clinicalsys.nurse on clinicalsys.nurse.id=staff.id WHERE staff.clinic_id in (SELECT clinic.id " +
            "FROM clinic WHERE clinic.clinic_name = ?1)", nativeQuery = true)
    Set<Nurse> findByClinicName(String clinicName);

}
