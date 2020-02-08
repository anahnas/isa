package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    Drug findDrugByDrugName(String drugName);

    @Query("select d from Drug d")
    public Set<Drug> allDrugs();

    @Query(value = "select * from clinicalsys.drug  where clinicalsys.drug.id = ?1", nativeQuery = true)
    Drug findByIdMy(Long l);

}
