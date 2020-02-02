package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Long> {

    @Query(value = "SELECT * FROM doctor INNER JOIN clinicalsys.user ON doctor.id = clinicalsys.user.id INNER JOIN " +
            "clinicalsys.staff ON doctor.id=clinicalsys.staff.id WHERE doctor.id in " +
            "(SELECT doctor_specializations.doctor_id FROM doctor_specializations WHERE " +
            "doctor_specializations.specializations_id = ?1)", nativeQuery = true)
    Set<Doctor> allWithSpecialization(Long specialisationId);
}
