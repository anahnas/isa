package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {

    @Query("select ap from Appointment ap")
    Set<Appointment> getAllAppintments();

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE appointment SET room_id=?1 where id = ?2", nativeQuery = true)
    void addRoom(Long roomId, Long appId);
}