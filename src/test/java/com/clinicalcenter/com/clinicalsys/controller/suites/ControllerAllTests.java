package com.clinicalcenter.com.clinicalsys.controller.suites;

import com.clinicalcenter.com.clinicalsys.controller.PatientControllerIT;
import com.clinicalcenter.com.clinicalsys.controller.PatientControllerUnitTest;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        PatientControllerIT.class,
        PatientControllerUnitTest.class
})
public class ControllerAllTests {


}
