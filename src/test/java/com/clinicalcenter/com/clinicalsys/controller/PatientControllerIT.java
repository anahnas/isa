package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.constants.PatientConstants;
import com.clinicalcenter.com.clinicalsys.constants.UserConstants;
import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.DTO.AppTypeDTO;
import com.clinicalcenter.com.clinicalsys.model.DTO.Doctor_FreeTimes;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationRequest;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationResponse;
import com.clinicalcenter.com.clinicalsys.repository.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Objects;
import java.util.Set;

import static org.junit.Assert.*;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerIT {

    @Autowired
    private AppointmentRepository appointmentRepository;

    private TestRestTemplate restTemplate = new TestRestTemplate();
    private String accessToken;

    @Before
    public void setUp(){

    }


    private void loginAsPatient() {
        AuthenticationRequest loginDto = new AuthenticationRequest(
                UserConstants.DB_USERNAME_PATIENT, UserConstants.DB_PASSWORD_PATIENT
        );
        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity("http://localhost:8080/users/login", loginDto , AuthenticationResponse.class);
        AuthenticationResponse user = response.getBody();
        accessToken = Objects.requireNonNull(user).getToken();
    }

    private void loginAsDoctor() {
        AuthenticationRequest loginDto = new AuthenticationRequest(
                UserConstants.DB_USERNAME_DOCTOR, UserConstants.DB_PASSWORD_DOCTOR
        );
        ResponseEntity<AuthenticationResponse> response = restTemplate.postForEntity("http://localhost:8080/users/login", loginDto , AuthenticationResponse.class);
        AuthenticationResponse user = response.getBody();
        accessToken = Objects.requireNonNull(user).getToken();
        System.out.println(accessToken);
    }

    private HttpEntity<Object> createHttpEntity() {
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        return new HttpEntity<>(headers);
    }


    @Test
    public void testGetFreeSpecializedDoctorClinics_successful(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>() {});
        Object body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        HashSet<Clinic> clinics = (HashSet<Clinic>)body;
        assertNotNull(clinics);
        assertEquals(3,clinics.size());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_notExistingType(){
        //this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.NOT_EXISTING_TYPE);
        type.setId(PatientConstants.NOT_EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>(){});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_datePassed(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.PASSED_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>(){});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_notLogged(){
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.NOT_EXISTING_TYPE);
        type.setId(PatientConstants.NOT_EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>(){});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_loggedAsDoctor(){
        this.loginAsDoctor();
        AppTypeDTO type = new AppTypeDTO();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppTypeDTO> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>(){});
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_wrongDateFormat(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.DATE_WRONG_FORMAT, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>() {});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_bodyEmpty(){
        this.loginAsPatient();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(new AppointmentType(), headers);
        ResponseEntity<Set<Clinic>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Clinic>>(){});

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void getAppointmentTypes_successful(){
        this.loginAsPatient();

        ResponseEntity<Set<AppointmentType>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAppointmentTypes", HttpMethod.GET, createHttpEntity(), new ParameterizedTypeReference<Set<AppointmentType>>() {});
        Object body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        HashSet<AppointmentType> appointmentTypes = (HashSet<AppointmentType>)body;
        assertNotNull(appointmentTypes);
        assertEquals(10,appointmentTypes.size());
    }

    @Test
    public void getAppointmentTypes_loggedAsDoctor(){
        this.loginAsDoctor();
        ResponseEntity<Set<AppointmentType>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAppointmentTypes", HttpMethod.GET, createHttpEntity(), new ParameterizedTypeReference<Set<AppointmentType>>() {});
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void getAppointmentTypes_notLogged(){
        ResponseEntity<Set<AppointmentType>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAppointmentTypes", HttpMethod.GET, createHttpEntity(), new ParameterizedTypeReference<Set<AppointmentType>>() {});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_successful(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>() {});
        Object body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        HashSet<Doctor_FreeTimes> doctors = (HashSet<Doctor_FreeTimes>)body;
        assertNotNull(doctors);
        assertEquals(1,doctors.size());
    }

    @Test
    public void testGetFreeSpecializedDoctors_notExistingType(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.NOT_EXISTING_TYPE);
        type.setId(PatientConstants.NOT_EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE  + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>(){});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_datePassed(){
        //this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.PASSED_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>(){});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_notLogged(){
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>(){});
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_loggedAsDoctor(){
        this.loginAsDoctor();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>(){});
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_wrongDateFormat(){
        this.loginAsPatient();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.DATE_WRONG_FORMAT + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>() {});
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testGetFreeSpecializedDoctors_bodyEmpty(){
        this.loginAsPatient();
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(new AppointmentType(), headers);
        ResponseEntity<Set<Doctor_FreeTimes>> response = restTemplate.exchange(
                "http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME, HttpMethod.POST, request, new ParameterizedTypeReference<Set<Doctor_FreeTimes>>(){});

        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testRequestApp_successful(){
        this.loginAsPatient();
        int before = appointmentRepository.getAllAppintments().size();
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);
        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME + "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);
        String body = response.getBody();
        assertEquals(HttpStatus.OK, response.getStatusCode());
        Set<Appointment> set = appointmentRepository.getAllAppintments();
        assertEquals(set.size(), before + 1);


        Appointment last = null;
        Iterator iterator = set.iterator();
        while(iterator.hasNext()){
            last = (Appointment) iterator.next();
        }
        assertNotNull(Objects.requireNonNull(last).getDoctor());
        assertNotNull(last.getStartTime());
        assertNotNull(last.getEndTime());
        assertNotNull(last.getPatient());
        assertNotNull(last.getAppState());
        assertNotNull(last.getType());
        assertNull(last.getRoom());

        appointmentRepository.delete(last);
    }

    private void deleteAppointmentIfOneIsAdded(int before){
        Set<Appointment> set = appointmentRepository.getAllAppintments();
        if(before == set.size()-1){
            System.out.println("added");
            Appointment last = null;
            Iterator iterator = set.iterator();

            while(iterator.hasNext()){
                last = (Appointment) iterator.next();
            }
            appointmentRepository.delete(last);
            System.out.println("deleted");
        }
    }

    @Test
    public void testRequestApp_datePassed(){
        //this.loginAsPatient();

        AppTypeDTO type = new AppTypeDTO();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppTypeDTO> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.PASSED_DATE_TIME + "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testRequestApp_notLogged(){
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME + "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testRequestApp_wrongDateFormat(){
        this.loginAsPatient();

        // set request
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.DATE_WRONG_FORMAT + "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testRequestApp_bodyEmpty(){
        this.loginAsPatient();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(new AppointmentType(), headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME +  "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }


    @Test
    public void testRequestApp_NotExistingPatientEmail(){
        this.loginAsPatient();

        // set request
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME + "/" + UserConstants.DB_USERNAME_AVAILABLE_DOCTOR + "/" + UserConstants.NOT_EXISTING_DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testRequestApp_NotExistingDoctorMail(){
        this.loginAsPatient();

        // set request
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME + "/" + UserConstants.NOT_EXISTING_DB_USERNAME_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @Test
    public void testRequestApp_userNorLoggedIn(){
        // set request
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppointmentType> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.FUTURE_DATE_TIME + "/" + UserConstants.DB_USERNAME_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT_NOT_LOGGED_IN + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.FORBIDDEN, response.getStatusCode());
    }

    @Test
    public void testRequestApp_appointmentReserved(){
        //this.loginAsPatient();

        // set request
        AppTypeDTO type = new AppTypeDTO();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + this.accessToken);
        HttpEntity<AppTypeDTO> request = new HttpEntity<>(type, headers);

        int before = appointmentRepository.getAllAppintments().size();

        ResponseEntity<String> response = restTemplate.exchange(
                "http://localhost:8080/patient/requestApp/" + PatientConstants.ANOTHER_FUTURE_DATE_TIME + "/" + UserConstants.DB_USERNAME_DOCTOR + "/" + UserConstants.DB_USERNAME_PATIENT + ".", HttpMethod.POST, request, String.class);

        int after = appointmentRepository.getAllAppintments().size();

        deleteAppointmentIfOneIsAdded(before);
        assertEquals(before, after);
        assertEquals(HttpStatus.NOT_ACCEPTABLE, response.getStatusCode());
    }

    @After
    public void tearDown(){

    }/*
*/
}
