package com.clinicalcenter.com.clinicalsys.controller;

import com.clinicalcenter.com.clinicalsys.constants.PatientConstants;
import com.clinicalcenter.com.clinicalsys.constants.UserConstants;
import com.clinicalcenter.com.clinicalsys.model.Appointment;
import com.clinicalcenter.com.clinicalsys.model.AppointmentType;
import com.clinicalcenter.com.clinicalsys.model.Clinic;
import com.clinicalcenter.com.clinicalsys.model.DTO.Doctor_FreeTimes;
import com.clinicalcenter.com.clinicalsys.model.Doctor;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationRequest;
import com.clinicalcenter.com.clinicalsys.model.authentication.AuthenticationResponse;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.transaction.Transactional;
import java.io.IOException;
import java.util.*;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.http.MediaType.APPLICATION_JSON_UTF8;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class PatientControllerUnitTest {

    @Mock
    private DoctorRepository doctorRepository;
    @Mock
    private AppointmentTypeRepository appointmentTypeRepository;
    @Mock
    private AppointmentRepository appointmentRepository;
    @Mock
    private ClinicRespository clinicRespository;
    @Mock
    private ClinicAdminRepository clinicAdminRespository;
    @Mock
    private PatientRepository patientRespository;

    @InjectMocks
    PatientController patientControllerUnderTest;

    private MockMvc mockMvc;

    private ObjectMapper objectMapper = new ObjectMapper();


    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(patientControllerUnderTest).build();
        List<AppointmentType> types = new ArrayList<>();
        types.add(new AppointmentType()); types.add(new AppointmentType()); types.add(new AppointmentType());
        types.add(new AppointmentType()); types.add(new AppointmentType()); types.add(new AppointmentType());
        types.add(new AppointmentType()); types.add(new AppointmentType()); types.add(new AppointmentType());
        Mockito.when(appointmentTypeRepository.findAll()).thenReturn(types);
        Set<Doctor> doctors = new HashSet<>();
        Doctor d = new Doctor();
        d.setSsn("srsn");
        d.setEmail("email@mail.com");
        doctors.add(d); doctors.add(d); doctors.add(d);
        doctors.add(d); doctors.add(d); doctors.add(d);
        doctors.add(d); doctors.add(d); doctors.add(d);

        Mockito.when(doctorRepository.allWithSpecialization(1L)).thenReturn(doctors);
        Mockito.when(doctorRepository.allWithSpecializationInClinic(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID, 1L)).thenReturn(doctors);

        Clinic clinic = new Clinic();
        clinic.setId(1L);
        Mockito.when(clinicRespository.findByClinicName(PatientConstants.EXISTING_CLINIC_NAME)).thenReturn(clinic);
        Mockito.when(clinicRespository.findByClinicName(PatientConstants.NOT_EXISTING_CLINIC_NAME)).thenReturn(null);
    }

    @Test
    public void getAppointmentTypes_successful() throws Exception {
        //this.loginAsPatient();

        mockMvc.perform(get("http://localhost:8080/patient/getAppointmentTypes")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(appointmentTypeRepository, times(1)).findAll();
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_successful() throws Exception {
        AppointmentType appointmentType = new AppointmentType();
        appointmentType.setId(1L);
        appointmentType.setType("Ocni pregled");

        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(appointmentType)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());

        verify(doctorRepository, times(1)).allWithSpecialization(1L);

    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_notExistingType() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.NOT_EXISTING_TYPE);
        type.setId(PatientConstants.NOT_EXISTING_APPOINTMENT_TYPE_ID);


        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.FUTURE_DATE).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$").isArray());

    }



    @Test
    public void testGetFreeSpecializedDoctorClinics_datePassed() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);

        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.PASSED_DATE).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isNotAcceptable())
                .andExpect(jsonPath("$").isArray());
    }

    @Test
    public void testGetFreeSpecializedDoctorClinics_wrongDateFormat() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/" + PatientConstants.DATE_WRONG_FORMAT).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print()).andExpect(status().isNotAcceptable());
    }



    @Test
    public void testGetFreeSpecializedDoctors_successful() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);

        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/"  + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isOk()).andExpect(jsonPath("$").isArray());
        verify(doctorRepository, times(1)).allWithSpecialization(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
    }

    @Test
    public void testGetFreeSpecializedDoctors_notExistingType() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.NOT_EXISTING_TYPE);
        type.setId(PatientConstants.NOT_EXISTING_APPOINTMENT_TYPE_ID);

        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/"  + PatientConstants.FUTURE_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isNotAcceptable());
    }



    @Test
    public void testGetFreeSpecializedDoctors_datePassed() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/"  + PatientConstants.PASSED_DATE + "/" + PatientConstants.EXISTING_CLINIC_NAME).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isNotAcceptable());
    }




    @Test
    public void testGetFreeSpecializedDoctors_wrongDateFormat() throws Exception {
        AppointmentType type = new AppointmentType();
        type.setType(PatientConstants.EXISTING_TYPE);
        type.setId(PatientConstants.EXISTING_APPOINTMENT_TYPE_ID);
        mockMvc.perform(post("http://localhost:8080/patient/getAvailableClinics/"  + PatientConstants.DATE_WRONG_FORMAT + "/" + PatientConstants.EXISTING_CLINIC_NAME).contentType(APPLICATION_JSON).content(objectMapper.writeValueAsString(type)).characterEncoding("utf-8")).andDo(print())
                .andExpect(status().isNotAcceptable());
    }


    @After
    public void tearDown(){

    }



}
