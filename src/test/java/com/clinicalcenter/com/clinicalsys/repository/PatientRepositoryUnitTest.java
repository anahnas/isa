package com.clinicalcenter.com.clinicalsys.repository;

import com.clinicalcenter.com.clinicalsys.model.Patient;
import com.clinicalcenter.com.clinicalsys.model.User;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace= AutoConfigureTestDatabase.Replace.NONE)
public class PatientRepositoryUnitTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private PatientRepository patientRepository;

    @Test
    public void whenGetAllPatients_thenReturnAllPatients(){
        entityManager.clear();
        User user = new User();
        user.setEmail("email@gmail.com");
        user.setId(30L);
        user.setSsn("srsn");
        user.setActive(false);
        user.setRole(RoleEnum.PATIENT);
        user.setAdminConfirmed(false);
        user.setPassword("as");
        user.setFirstName("as");
        user.setLastName("as");
        Patient patient = new Patient(user);
        entityManager.persist(patient);
        entityManager.flush();

        Set<Patient> all = patientRepository.getAllPatients();
        assertEquals(5, all.size());    // 4 already added and one added with persistanceManager
        entityManager.remove(patient);
    }

}

