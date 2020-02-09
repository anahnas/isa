package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentTypeRepository extends JpaRepository<AppointmentType, Long> {

    @Query(value = "Select a from  AppointmentType a where a.id=?1")
    AppointmentType findByIdMy(Long id);

    //
    // AppointmentType findType(String type);
}
