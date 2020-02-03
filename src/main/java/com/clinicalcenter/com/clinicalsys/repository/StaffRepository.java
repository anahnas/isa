package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.Staff;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface StaffRepository extends JpaRepository<Staff,Long> {

    Staff findByEmail(String email);
   /* @Query(value = "SELECT * FROM appointment WHERE appointment.app_state=1 or appointment.app_state=3 or " +
            "appointment.app_state=4 and appointment.id in (SELECT staff.id FROM staff INNER JOIN  clinicalsys.user on " +
            "staff.id = clinicalsys.user.id inner join staff_appointments sa on staff.id = sa.staff_id where " +
            "clinicalsys.user.email = 'jpavlovic0@google.com.au')", nativeQuery = true)
    Set<Appointment> getAppointments(String email);*/
}
