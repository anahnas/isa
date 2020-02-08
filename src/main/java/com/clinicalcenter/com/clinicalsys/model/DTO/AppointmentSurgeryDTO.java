package com.clinicalcenter.com.clinicalsys.model.DTO;

public class AppointmentSurgeryDTO {

    private String appointmentType;
    private String DoctorsName;
    private Long DoctorId;
    private Integer DoctorGrade;
    private String PatientName;
    private String dateString;
    private String clinicName;
    private Long ClinicId;
    private Integer ClinicGrade;
    private Long id;

    public  AppointmentSurgeryDTO(){

    }

    public AppointmentSurgeryDTO(String appointmentType, String doctorsName, String patientName, String dateString,
                                 String clinicName, Long id, Long DoctorId, Long ClinicId,
                                 Integer clinicGrade, Integer doctorGrade) {
        this.appointmentType = appointmentType;
        DoctorsName = doctorsName;
        PatientName = patientName;
        this.dateString = dateString;
        this.clinicName = clinicName;
        this.id = id;
        this.DoctorId = DoctorId;
        this.ClinicId = ClinicId;
        this.ClinicGrade = clinicGrade;
        this.DoctorGrade = doctorGrade;
    }

    public Integer getDoctorGrade() {
        return DoctorGrade;
    }

    public void setDoctorGrade(Integer doctorGrade) {
        DoctorGrade = doctorGrade;
    }

    public Integer getClinicGrade() {
        return ClinicGrade;
    }

    public void setClinicGrade(Integer clinicGrade) {
        ClinicGrade = clinicGrade;
    }

    public Long getDoctorId() {
        return DoctorId;
    }

    public void setDoctorId(Long doctorId) {
        DoctorId = doctorId;
    }

    public Long getClinicId() {
        return ClinicId;
    }

    public void setClinicId(Long clinicId) {
        ClinicId = clinicId;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public String getDoctorsName() {
        return DoctorsName;
    }

    public void setDoctorsName(String doctorsName) {
        DoctorsName = doctorsName;
    }

    public String getPatientName() {
        return PatientName;
    }

    public void setPatientName(String patientName) {
        PatientName = patientName;
    }

    public String getDateString() {
        return dateString;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
    }

    public String getClinicName() {
        return clinicName;
    }

    public void setClinicName(String clinicName) {
        this.clinicName = clinicName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}