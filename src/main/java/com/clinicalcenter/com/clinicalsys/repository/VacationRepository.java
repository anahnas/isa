package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.VacationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface VacationRepository extends JpaRepository<VacationRequest,Long> {

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE VacationRequest v SET v.accepted = true where v.id = ?1")
    void acceptRequest(Long vacId);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query("UPDATE VacationRequest v SET v.accepted = false where v.id = ?1")
    void denyRequest(Long vacId);

    @Query("select  v From VacationRequest v where v.id = ?1")
    VacationRequest findByIdMy(Long vacId);
}
