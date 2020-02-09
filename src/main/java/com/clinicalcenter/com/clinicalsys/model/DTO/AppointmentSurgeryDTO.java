package com.clinicalcenter.com.clinicalsys.model.DTO;

public class AppointmentSurgeryDTO {

    private String appointmentType;
    private String DoctorsName;
    private Long DoctorId;
    private Integer DoctorGrade;
    private String PatientName;
    private Long PatientId;
    private String dateString;
    private String clinicName;
    private Long ClinicId;
    private Integer ClinicGrade;
    private Double price;
    private Double discount;
    private Long id;

    public  AppointmentSurgeryDTO(){

    }

    public AppointmentSurgeryDTO(String appointmentType, String doctorsName, String patientName, String dateString,
                                 String clinicName, Long id, Long DoctorId, Long ClinicId, Long PatinetId,
                                 Integer clinicGrade, Integer doctorGrade, Double price,Double discount) {
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
        this.PatientId = PatinetId;
        this.price = price;
        this.discount = discount;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getDiscount() {
        return discount;
    }

    public void setDiscount(Double discount) {
        this.discount = discount;
    }

    public Long getPatientId() {
        return PatientId;
    }

    public void setPatientId(Long patientId) {
        PatientId = patientId;
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
