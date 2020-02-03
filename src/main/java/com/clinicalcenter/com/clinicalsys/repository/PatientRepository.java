package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {

    Patient findByEmail(String email);

    @Query("select p from Patient p")
    Set<Patient> getAllPatients();

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Patient p SET p.active = true where p.email = email")
    void setActive(String email);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE Patient p SET p.adminConfirmed = true where p.email = email")
    void setConfirmed(String email);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE clinicalsys.user SET first_name=?1, last_name = ?2, password=?3, " +
            "address=?4, city=?5, phone_number=?6, country=?7 where email = ?8", nativeQuery = true)
    void editPatient(String firstName, String lastName, String password, String address, String city,
                     String phoneNumber, String country, String email_test);

    @Query(value = "SELECT p.id from Patient p where p.email=email")
    Long getId(String email);

}
