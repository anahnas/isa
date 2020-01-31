package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}
