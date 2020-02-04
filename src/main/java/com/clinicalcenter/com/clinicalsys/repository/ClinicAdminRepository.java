package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.ClinicAdmin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface ClinicAdminRepository extends JpaRepository<ClinicAdmin, Long> {

    ClinicAdmin findByEmail(String email);

    /*Returns all admins of a clinic that a given doctor works for*/
    @Query(value = "SELECT * FROM clinicalsys.user INNER JOIN clinic_admin on clinicalsys.user.id=clinic_admin.id " +
            "WHERE clinic_admin.clinic_id in (SELECT staff.clinic_id FROM staff INNER JOIN clinicalsys.user ON " +
            "clinicalsys.user.id = staff.id WHERE clinicalsys.user.email = ?1)", nativeQuery = true)
    Set<ClinicAdmin> getByDoctorEmail(String doctor_email);
}
