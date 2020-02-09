package com.clinicalcenter.com.clinicalsys.repository.suites;

import com.clinicalcenter.com.clinicalsys.controller.PatientControllerIT;
import com.clinicalcenter.com.clinicalsys.controller.PatientControllerUnitTest;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepositoryIntegrationTest;
import com.clinicalcenter.com.clinicalsys.repository.PatientRepositoryUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientRepositoryUnitTest.class,
        PatientRepositoryIntegrationTest.class
})
public class RepositoryAllTests {


}
