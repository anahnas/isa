package com.clinicalcenter.com.clinicalsys.repository;

        import com.clinicalcenter.com.clinicalsys.model.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Modifying;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import javax.transaction.Transactional;
        import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);

    @Query("SELECT u FROM User u WHERE u.adminConfirmed = false ")
    Set<User> findRequests();

    @Query("SELECT u FROM User u WHERE u.role = 1 ")
    Set<User> findClinicAdmins();

    @Query("SELECT u FROM User u WHERE u.role = 3 or u.role = 4")
    Set<User> findStaff();

    @Query("SELECT u FROM User u WHERE u.active = true and u.role = 2")
    Set<User> allPatients();

    @Transactional
    @Modifying
    @Query("update User u SET u.password = ?2 where u.email= ?1")
    void updatePassword(String email, String newpassword);

    @Transactional
    @Modifying
    @Query("update User u SET u.firstLogin = false where u.email= ?1")
    void changeFirstLogin(String email);
}
