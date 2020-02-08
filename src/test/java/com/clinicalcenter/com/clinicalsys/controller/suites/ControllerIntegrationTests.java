package com.clinicalcenter.com.clinicalsys.controller.suites;

import com.clinicalcenter.com.clinicalsys.controller.PatientControllerIntegrationTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientControllerIntegrationTest.class
})
public class ControllerIntegrationTests {

}
