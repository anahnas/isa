package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    @Query(value = "Select r from Room r where r.id = ?1")
    Room findByIdMy(Long id);

    Room findRoomByName(String name);

    @Transactional
    void deleteByName(String name);
}
