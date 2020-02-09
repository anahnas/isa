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

    @Query(value = "Select a from  Appointment a where a.id=?1")
    Appointment findByIdMy(Long id);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE appointment SET room_id=?1 where id = ?2", nativeQuery = true)
    void addRoom(Long roomId, Long appId);

    @Query(value = "SELECT a from Appointment a where a.patient.id=?1 and a.appState=4")
    Set<Appointment> getPatientsPastAppointments(Long patientid);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "UPDATE appointment SET patient_id=?1, app_state=5 where id=?2 ", nativeQuery = true)
    void bookFastApp(Long patientId, Long id);

    @Transactional
    @Modifying(flushAutomatically = true)
    @Query(value = "DELETE FROM clinicalsys.clinic_predefined_appointments WHERE " +
            "clinicalsys.clinic_predefined_appointments.predefined_appointments_id=?1", nativeQuery = true)
    void deletePredefinedAppointment(Long id);
}
