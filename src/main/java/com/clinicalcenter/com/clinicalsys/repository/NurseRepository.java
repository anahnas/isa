package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Nurse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NurseRepository extends JpaRepository<Nurse, Long> {
}
