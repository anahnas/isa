package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Diagnose;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface DiagnoseRepository extends JpaRepository<Diagnose, Long> {

    Diagnose findByName(String name);

    @Query(value = "select * from clinicalsys.diagnose", nativeQuery = true)
    Set<Diagnose> allDiagnoses();

    @Query(value = "select * from clinicalsys.diagnose where clinicalsys.diagnose.id = ?1", nativeQuery = true)
    Diagnose findByIdMy(long parseLong);
}
