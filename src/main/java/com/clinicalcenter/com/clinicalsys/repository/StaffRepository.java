package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;

import javax.transaction.Transactional;
import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    Staff findByEmail(String email);

    /*@Transactional(Transactional.TxType.REQUIRES_NEW)
    Staff findByName(String name);*/
}
