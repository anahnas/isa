package com.clinicalcenter.com.clinicalsys.repository;

        import com.clinicalcenter.com.clinicalsys.dto.UserDTO;
        import com.clinicalcenter.com.clinicalsys.model.User;
        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import javax.transaction.Transactional;
        import java.util.Set;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

    @Transactional
    void deleteByEmail(String email);



    @Query("SELECT u FROM User u WHERE u.active = false ")
    Set<User> findRequests();
}
