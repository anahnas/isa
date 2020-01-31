package com.clinicalcenter.com.clinicalsys.util;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;

@Component
public class StartupInitialization implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    DoctorRepository doctorRepository;
    @Autowired
    ClinicRespository clinicRespository;
    @Autowired
    PatientRepository patientRepository;
    @Autowired
    StaffRepository staffRepository;
    @Autowired
    ClinicAdminRepository clinicAdminRepository;
    @Autowired
    ClinicCenterAdminRepository clinicCenterAdminRepository;
    @Autowired
    NurseRepository nurseRepository;
    @Autowired
    DrugRepository drugRepository;
    @Autowired
    DiagnoseRepository diagnoseRepository;
    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {

        //region ClinicCenterAdmin
        User user_ccadmin = new User("kmalia8@phoca.cz","Kial","Malia","663354",
                "1 Holy Cross Center","Barajevo","Serbia",
                "(407) 1040666","6199927252131");
        ClinicCenterAdmin ccadmin = new ClinicCenterAdmin(user_ccadmin,true);
        clinicCenterAdminRepository.save(ccadmin);
        //endregion

        //region Drug
        Drug drug1 = new Drug("Cefaleksin", "Lek protiv upalnih tipova bolova",240.0);
        drugRepository.save(drug1);

        Drug drug2 = new Drug("Xalol", "Protiv napada panike + dobro ide uz alkohol",440.0);
        drugRepository.save(drug2);

        Drug drug3 = new Drug("Morfijum", "Protiv jakih bolova",940.0);
        drugRepository.save(drug3);

        Drug drug4 = new Drug("Vervex", "Resava glavobolje i bol grla",62.0);
        drugRepository.save(drug4);
        //endregion

        //region Rooms
        Room clinicns_1_e = new Room ("Soba za preglede 1", RoomType.EXAMINATION);
        Room clinicns_2_e = new Room ("Soba za preglede 2", RoomType.EXAMINATION);
        Room clinicns_1_s = new Room ("Operaciona sala", RoomType.SURGERY);

        Room clinicbg_1_e = new Room ("Soba za preglede 1", RoomType.EXAMINATION);
        Room clinicbg_2_e = new Room ("Soba za preglede 2", RoomType.EXAMINATION);
        Room clinicbg_1_s = new Room ("Glavna operaciona sala", RoomType.SURGERY);
        Room clinicbg_2_s = new Room ("Pomocna operaciona sala", RoomType.SURGERY);

        Room clinicsu_1_e = new Room ("Soba za preglede 1", RoomType.EXAMINATION);
        Room clinicsu_2_e = new Room ("Soba za preglede 2", RoomType.EXAMINATION);
        Room clinicsu_3_e = new Room ("Soba za preglede 3", RoomType.EXAMINATION);
        Room clinicsu_1_s = new Room ("Sala za operacije", RoomType.SURGERY);

        Room clinicvl_1_e = new Room("Jedina soba...", RoomType.EXAMINATION);
        //endregion

        //region Diagnoses
        Diagnose diagnose1 = new Diagnose("Upala pluca",
                "Lorem ipsum dolor sit amet, consectetur adipiscing elit.");
        diagnoseRepository.save(diagnose1);

        Diagnose diagnose2 = new Diagnose("Rak slepog creva",
                "Duis pretium sed sapien at pellentesque. ");
        diagnoseRepository.save(diagnose2);

        Diagnose diagnose3 = new Diagnose("Upala pluca",
                "Fusce pharetra sem nisl, non gravida neque vulputate in. Praesent nec augue odio amet.");
        diagnoseRepository.save(diagnose3);

        Diagnose diagnose4 = new Diagnose("Upala pluca",
                "Vivamus mattis pellentesque augue nec nullam. ");
        diagnoseRepository.save(diagnose4);
        //endregion

        //region AppointmentTypes
            AppointmentType clinicns_a1 = new AppointmentType("Ocni pregled", 1400.0, 1.0);
            AppointmentType clinicns_a2 = new AppointmentType("Pregled grla", 750.0, 1.0);
            AppointmentType clinicns_a3 = new AppointmentType("Pregled sluha", 920.0, 0.85);

            AppointmentType clinicbg_a1 = new AppointmentType("Pregled vida", 1600.0, 1.0);
            AppointmentType clinicbg_a2 = new AppointmentType("Pregled pluca", 920.0, 1.0);
            AppointmentType clinicbg_a3 = new AppointmentType("Alergo test", 2100.0, 0.85);
            AppointmentType clinicbg_a4 = new AppointmentType("Merenje holesterola", 880.0, 0.95);

            AppointmentType clinicsu_a1 = new AppointmentType("Sistematski pregled", 1050.0,1.0);
            AppointmentType clinicsu_a2 = new AppointmentType("Stomatoloski pregled", 2400.0,0.9);

            AppointmentType clinicvl_a1 = new AppointmentType("Rutinska kontrola", 450.0,1.0);
        //endregion

        //region Clinics
        Clinic clinicns = new Clinic("Klinicki Centar Novi Sad", "Bulevar Cara Lazara 18",
                null, null, "Novi Sad", "Srbija");
        HashSet<Room> rooms = new HashSet<Room>();
        rooms.add(clinicns_1_e);rooms.add(clinicns_2_e);rooms.add(clinicns_1_s);
        clinicns.setRooms(rooms);
        HashSet<AppointmentType> appointmentTypes = new HashSet<AppointmentType>();
        appointmentTypes.add(clinicns_a1);appointmentTypes.add(clinicns_a2);appointmentTypes.add(clinicns_a3);
        clinicns.setAppointmentTypes(appointmentTypes);

        Clinic clinicbg = new Clinic("Centralna Klinika Beograd", "Nemanjina 163a",
                null, null, "Beograd", "Srbija");
        rooms = new HashSet<Room>();
        rooms.add(clinicbg_1_e);rooms.add(clinicbg_2_e);rooms.add(clinicbg_1_s);rooms.add(clinicbg_1_s);
        clinicbg.setRooms(rooms);
        appointmentTypes = new HashSet<AppointmentType>();
        appointmentTypes.add(clinicbg_a1);appointmentTypes.add(clinicbg_a2);appointmentTypes.add(clinicbg_a3);
        appointmentTypes.add(clinicbg_a4);
        clinicbg.setAppointmentTypes(appointmentTypes);

        Clinic clinicsu = new Clinic("Okruzna Bolnica", "Kerska 34",
                null, null, "Subotica", "Srbija");
        rooms = new HashSet<Room>();
        rooms.add(clinicsu_1_e);rooms.add(clinicsu_2_e);rooms.add(clinicsu_1_s);rooms.add(clinicsu_3_e);
        clinicsu.setRooms(rooms);
        appointmentTypes = new HashSet<AppointmentType>();
        appointmentTypes.add(clinicsu_a1);appointmentTypes.add(clinicsu_a2);
        clinicsu.setAppointmentTypes(appointmentTypes);


        Clinic clinicvl = new Clinic("Mesna Ambulanta Vlasenica", "Brdskih Heroja 34",
                null, null, "Vlasenica", "Bosna i Hercegovina");
        rooms = new HashSet<Room>();
        rooms.add(clinicvl_1_e);
        clinicvl.setRooms(rooms);
        appointmentTypes = new HashSet<AppointmentType>();
        appointmentTypes.add(clinicvl_a1);
        clinicvl.setAppointmentTypes(appointmentTypes);

        clinicRespository.save(clinicns);
        clinicRespository.save(clinicbg);
        clinicRespository.save(clinicsu);
        clinicRespository.save(clinicvl);
        //endregion

        //region Patients
        User user_patient1 = new User("grubinow8@php.net","Gretna","Rubinow",
                "117565","88529 Fairfield Parkway","Budapest","Hungary",
                "(682) 9443368","9598205971511");
        Patient patient1 = new Patient(user_patient1);
        patientRepository.save(patient1);
        //endregion

        //region ClinicAdmin
        User user_cadminbg = new User("wdeekes5@tmall.com,Whittaker","Deekes","318914",
                "6 Brentwood Way","Savski","Venac","Serbia",
                "(141) 9739447","67607378738");
        ClinicAdmin cadmin1 = new ClinicAdmin(user_cadminbg,clinicbg);
        clinicAdminRepository.save(cadmin1);
        User user_cadminns = new User("gprophete@acquirethisname.com","Gustavus","Prophet",
                "519930","277 Quincy Lane","Radojevo","Serbia",
                "(691) 5982347","4498498325468");
        ClinicAdmin cadmin2 = new ClinicAdmin(user_cadminns,clinicns);
        clinicAdminRepository.save(cadmin2);
        User user_cadminsu = new User("aashton7@acquirethisname.com","Adrianne","Ashton",
                "893866","29 Melvin Alley","Radenka","Serbia",
                "(263) 5567560","5057737453126");
        ClinicAdmin cadmin3 = new ClinicAdmin(user_cadminsu,clinicsu);
        clinicAdminRepository.save(cadmin3);
        User user_cadminvl = new User("avanrembrandt8@ezinearticles.com","Ashley",
                "Van Rembrandt","651346","610 Forster Point","Kovilj",
                "Serbia","(929) 7283860","9967156809229");
        ClinicAdmin cadmin4 = new ClinicAdmin(user_cadminvl,clinicvl);
        clinicAdminRepository.save(cadmin4);
        //endregion

        //region Doctors
        User user_doctor_1 = new User("jpavlovic0@google.com.au","Justus","Pavlovic",
                "557369","810 Cherokee Lane", "Kuštilj","Srbija",
                "(682) 9409237","8657870655830");
        user_doctor_1.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_1 = new Staff(user_doctor_1, clinicbg);
        staffRepository.save(staff_doctor_1);
        Doctor doctor1 = new Doctor(staff_doctor_1);
        doctorRepository.save(doctor1);

        User user_doctor_2 = new User("jmingardo1@howstuffworks.com","Judith","Mingardo",
                "638473","33 7th Crossing","Gyor","Madjarska",
                "(666) 6994851","1715377197364");
        user_doctor_1.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_2 = new Staff(user_doctor_2, clinicbg);
        Doctor doctor2 = new Doctor(staff_doctor_2);
        doctorRepository.save(doctor2);

        User user_doctor_3 = new User("nattle4@hhs.gov","Nigel","Attle","320532",
                "5 International Place","Kamenica", "Srbija","(466) 6189663",
                "9028453261450");
        user_doctor_1.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_3 = new Staff(user_doctor_3, clinicns);
        Doctor doctor3 = new Doctor(staff_doctor_3);
        doctorRepository.save(doctor3);

        User user_doctor_4 = new User("gstuchbery6@cargocollective.com","Gabriell",
                "Stuchbery","688918", "67 Little Fleur Plaza","Bogojevo",
                "Srbija","(376) 9635234","2697619396679");
        user_doctor_4.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_4 = new Staff(user_doctor_4, clinicns);
        Doctor doctor4 = new Doctor(staff_doctor_4);
        doctorRepository.save(doctor4);

        User user_doctor_5 = new User("kbalbeck9@java.com","Karyn","Balbeck",
                "695696","8 Ilene Junction","Budapest","Hungary",
                "(587) 8660314","8518068206648");
        user_doctor_5.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_5 = new Staff(user_doctor_5, clinicsu);
        Doctor doctor5 = new Doctor(staff_doctor_5);
        doctorRepository.save(doctor5);

        User user_doctor_6 = new User("aarne1@fastcompany.com","Ariela","Arne",
                "658385","910 Westend Terrace","Potoci","Bosna i Hercegovina",
                "(307) 8351741","6437437353973");
        user_doctor_6.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_6 = new Staff(user_doctor_6, clinicvl);
        Doctor doctor6 = new Doctor(staff_doctor_6);
        doctorRepository.save(doctor6);
        //endregion

        //region Nurses
            User user_nurse_ns1 = new User("rkitchinghamd@salon.com","Robinette","Kitchingham",
                "272851","1715 Boyd Place","Nova Pazova","Serbia",
                "(687) 2189973","8211024836776");
            Staff staff_nurse_ns1 = new Staff(user_nurse_ns1,clinicns);
            Nurse nurse_ns1 =new Nurse(staff_nurse_ns1);
            nurseRepository.save(nurse_ns1);

            User user_nurse_bg1 = new User("dbishopp4@wikispaces.com","Delcine","Bishopp",
                    "785391","1810 Michigan Way", "Balatun","Bosnia and Herzegovina",
                    "(192) 8186705","6771172485802");
            Staff staff_nurse_bg1 = new Staff(user_nurse_bg1,clinicbg);
            Nurse nurse_bg1 =new Nurse(staff_nurse_bg1);
            nurseRepository.save(nurse_bg1);

            User user_nurse_su1 = new User("sscholte5@examiner.com","Sydney","Scholte",
                    "401257","48 Hoard Hill","Lipci","Montenegro",
                    "(286) 9748369","3636790947925");
            Staff staff_nurse_su1 = new Staff(user_nurse_su1,clinicsu);
            Nurse nurse_su1 =new Nurse(staff_nurse_su1);
            nurseRepository.save(nurse_su1);

            User user_nurse_vl1 = new User("rkitchinghamd@salon.com","Robinette","Kitchingham",
                    "272851","1715 Boyd Place","Nova Pazova","Serbia",
                    "(687) 2189973","8211024836776");
            Staff staff_nurse_vl1 = new Staff(user_nurse_vl1,clinicvl);
            Nurse nurse_vl1 =new Nurse(staff_nurse_vl1);
            nurseRepository.save(nurse_vl1);

        //endregion

        //region Appointments
        Appointment ap_req1 = new Appointment(new Date(),null, clinicns_a1, patient1, null, doctor1);
        patient1.getAppointments().add(ap_req1);
        patientRepository.save(patient1);
        //endregion
    }
}
