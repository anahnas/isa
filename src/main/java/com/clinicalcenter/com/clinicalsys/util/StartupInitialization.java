package com.clinicalcenter.com.clinicalsys.util;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

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
    @Autowired
    AppointmentTypeRepository appointmentTypeRepository;
    @Autowired
    RoomRepository roomRepository;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent contextRefreshedEvent) {
/*
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

        ArrayList<Drug> drugs =new ArrayList<Drug>();
        drugs.add(drug1);drugs.add(drug2);drugs.add(drug3);drugs.add(drug4);
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
            AppointmentType appointmentType1 = new AppointmentType("Ocni pregled");
            AppointmentType appointmentType2 = new AppointmentType("Pregled grla");
            AppointmentType appointmentType3 = new AppointmentType("Pregled sluha");
            AppointmentType appointmentType4 = new AppointmentType("Pregled vida");
            AppointmentType appointmentType5 = new AppointmentType("Pregled pluca");
            AppointmentType appointmentType6 = new AppointmentType("Alergo test");
            AppointmentType appointmentType7 = new AppointmentType("Merenje holesterola");
            AppointmentType appointmentType8 = new AppointmentType("Sistematski pregled");
            AppointmentType appointmentType9 = new AppointmentType("Stomatoloski pregled");
            AppointmentType appointmentType10 = new AppointmentType("Rutinska kontrola");

            appointmentTypeRepository.save(appointmentType1);
            appointmentTypeRepository.save(appointmentType2);
            appointmentTypeRepository.save(appointmentType3);
            appointmentTypeRepository.save(appointmentType4);
            appointmentTypeRepository.save(appointmentType5);
            appointmentTypeRepository.save(appointmentType6);
            appointmentTypeRepository.save(appointmentType7);
            appointmentTypeRepository.save(appointmentType8);
            appointmentTypeRepository.save(appointmentType9);
            appointmentTypeRepository.save(appointmentType10);
        //endregion

        //region Clinics
        Clinic clinicns = new Clinic("Klinicki Centar Novi Sad", "Bulevar Cara Lazara 18",
                null, 8.3, "Novi Sad", "Srbija");
        HashSet<Room> rooms = new HashSet<Room>();
        rooms.add(clinicns_1_e);rooms.add(clinicns_2_e);rooms.add(clinicns_1_s);
        clinicns.setRooms(rooms);

        Clinic clinicbg = new Clinic("Centralna Klinika Beograd", "Nemanjina 163a",
                null, 9.2, "Beograd", "Srbija");
        rooms = new HashSet<Room>();
        rooms.add(clinicbg_1_e);rooms.add(clinicbg_2_e);rooms.add(clinicbg_1_s);rooms.add(clinicbg_2_s);
        clinicbg.setRooms(rooms);

        Clinic clinicsu = new Clinic("Okruzna Bolnica", "Kerska 34",
                null, 9.99, "Subotica", "Srbija");
        rooms = new HashSet<Room>();
        rooms.add(clinicsu_1_e);rooms.add(clinicsu_2_e);rooms.add(clinicsu_1_s);rooms.add(clinicsu_3_e);
        clinicsu.setRooms(rooms);

        Clinic clinicvl = new Clinic("Mesna Ambulanta Vlasenica", "Brdskih Heroja 34",
                null, 5.2, "Vlasenica", "Bosna i Hercegovina");
        rooms = new HashSet<Room>();
        rooms.add(clinicvl_1_e);
        clinicvl.setRooms(rooms);

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
        patient1.setActive(Boolean.TRUE);
        patientRepository.save(patient1);
        //endregion

        //region ClinicAdmin
        User user_cadminbg = new User("wdeekes5@tmall.com,Whittaker","Deekes","Mills",
                "318914","6 Brentwood Way","Savski Venac","Serbia",
                "(141) 9739447","67607378738");
        ClinicAdmin cadmin1 = new ClinicAdmin(user_cadminbg,clinicbg);
        clinicAdminRepository.save(cadmin1);
        User user_cadminns = new User("maxi.maksimovic7@gmail.com","Gustavus","Prophet",
                "519930","277 Quincy Lane","Radojevo","Serbia",
                "(691) 5982347","4498498325468");
        ClinicAdmin cadmin2 = new ClinicAdmin(user_cadminns,clinicns);
        cadmin2 = clinicAdminRepository.save(cadmin2);
        User user_cadminsu = new User("aashton7@acquirethisname.com","Adrianne","Ashton",
                "893866","29 Melvin Alley","Radenka","Serbia",
                "(263) 5567560","5057737453126");
        ClinicAdmin cadmin3 = new ClinicAdmin(user_cadminsu,clinicsu);
        cadmin3 = clinicAdminRepository.save(cadmin3);
        User user_cadminvl = new User("avanrembrandt8@ezinearticles.com","Ashley",
                "Van Rembrandt","651346","610 Forster Point","Kovilj",
                "Serbia","(929) 7283860","9967156809229");
        ClinicAdmin cadmin4 = new ClinicAdmin(user_cadminvl,clinicvl);
        cadmin4 = clinicAdminRepository.save(cadmin4);
        //endregion

        //region Doctors
        User user_doctor_1 = new User("jpavlovic0@google.com.au","Justus","Pavlovic",
                "557369","810 Cherokee Lane", "Kuštilj","Srbija",
                "(682) 9409237","8657870655830");
        user_doctor_1.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_1 = new Staff(user_doctor_1, clinicbg);
        Doctor doctor1 = new Doctor(staff_doctor_1);
        doctor1.addSpecialisation(appointmentType1);
        doctor1.addSpecialisation(appointmentType3);
        doctor1.addSpecialisation(appointmentType10);
        doctor1 = doctorRepository.save(doctor1);
        clinicbg.addNewAppTypePriceDiscount(appointmentType1,456.0,1.0);
        clinicbg.addNewAppTypePriceDiscount(appointmentType3,590.0,0.95);
        clinicbg.addNewAppTypePriceDiscount(appointmentType10,1200.0,0.8);
        clinicbg = clinicRespository.save(clinicbg);

        User user_doctor_2 = new User("jmingardo1@howstuffworks.com","Judith","Mingardo",
                "638473","33 7th Crossing","Gyor","Madjarska",
                "(666) 6994851","1715377197364");
        user_doctor_2.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_2 = new Staff(user_doctor_2, clinicbg);
        Doctor doctor2 = new Doctor(staff_doctor_2);
        doctor2.addSpecialisation(appointmentType2);
        doctor2.addSpecialisation(appointmentType6);
        doctor2.addSpecialisation(appointmentType8);
        doctor2.addSpecialisation(appointmentType7);
        doctor2 = doctorRepository.save(doctor2);
        clinicbg.addNewAppTypePriceDiscount(appointmentType2, 4580.0,0.75);
        clinicbg.addNewAppTypePriceDiscount(appointmentType6, 856.0,0.1);
        clinicbg.addNewAppTypePriceDiscount(appointmentType8, 380.0,0.1);
        clinicbg.addNewAppTypePriceDiscount(appointmentType7, 1200.0,0.5);
        clinicbg = clinicRespository.save(clinicbg);

        User user_doctor_3 = new User("nattle4@hhs.gov","Nigel","Attle","320532",
                "5 International Place","Kamenica", "Srbija","(466) 6189663",
                "9028453261450");
        user_doctor_3.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_3 = new Staff(user_doctor_3, clinicns);
        Doctor doctor3 = new Doctor(staff_doctor_3);
        doctor3.addSpecialisation(appointmentType1);
        doctor3.addSpecialisation(appointmentType4);
        doctor3.addSpecialisation(appointmentType8);
        doctor3.addSpecialisation(appointmentType6);
        doctor3 = doctorRepository.save(doctor3);
        clinicns.addNewAppTypePriceDiscount(appointmentType1,850.0,1.0);
        clinicns.addNewAppTypePriceDiscount(appointmentType4,180.0,1.0);
        clinicns.addNewAppTypePriceDiscount(appointmentType8,950.0,0.85);
        clinicns.addNewAppTypePriceDiscount(appointmentType6,1120.0,0.98);
        clinicns = clinicRespository.save(clinicns);

        User user_doctor_4 = new User("gstuchbery6@cargocollective.com","Gabriell",
                "Stuchbery","688918", "67 Little Fleur Plaza","Bogojevo",
                "Srbija","(376) 9635234","2697619396679");
        user_doctor_4.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_4 = new Staff(user_doctor_4, clinicns);
        Doctor doctor4 = new Doctor(staff_doctor_4);
        doctor4.addSpecialisation(appointmentType5);
        doctor4.addSpecialisation(appointmentType10);
        doctor4 = doctorRepository.save(doctor4);
        clinicns.addNewAppTypePriceDiscount(appointmentType5,920.0,1.0);
        clinicns.addNewAppTypePriceDiscount(appointmentType10,2200.0,0.7);
        clinicns = clinicRespository.save(clinicns);

        User user_doctor_5 = new User("kbalbeck9@java.com","Karyn","Balbeck",
                "695696","8 Ilene Junction","Budapest","Hungary",
                "(587) 8660314","8518068206648");
        user_doctor_5.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_5 = new Staff(user_doctor_5, clinicsu);
        Doctor doctor5 = new Doctor(staff_doctor_5);
        doctor5.addSpecialisation(appointmentType1);
        doctor5.addSpecialisation(appointmentType4);
        doctor5.addSpecialisation(appointmentType8);
        doctor5.addSpecialisation(appointmentType3);
        doctor5 = doctorRepository.save(doctor5);
        clinicsu.addNewAppTypePriceDiscount(appointmentType1,150.0,1.0);
        clinicsu.addNewAppTypePriceDiscount(appointmentType4,240.0,0.7);
        clinicsu.addNewAppTypePriceDiscount(appointmentType8,1500.0,0.5);
        clinicsu.addNewAppTypePriceDiscount(appointmentType3,720.0,1.0);
        clinicsu = clinicRespository.save(clinicsu);

        User user_doctor_6 = new User("aarne1@fastcompany.com","Ariela","Arne",
                "658385","910 Westend Terrace","Potoci","Bosna i Hercegovina",
                "(307) 8351741","6437437353973");
        user_doctor_6.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_6 = new Staff(user_doctor_6, clinicvl);
        Doctor doctor6 = new Doctor(staff_doctor_6);
        doctor6.addSpecialisation(appointmentType6);
        doctor6 = doctorRepository.save(doctor6);
        clinicvl.addNewAppTypePriceDiscount(appointmentType6, 860.0,0.9);
        clinicvl = clinicRespository.save(clinicvl);
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

            User user_nurse_vl1 = new User("nurse@nrs.com","Robinette","Kitchingham",
                    "123123","1715 Boyd Place","Nova Pazova","Serbia",
                    "(687) 2189973","8211024836776");
            Staff staff_nurse_vl1 = new Staff(user_nurse_vl1,clinicvl);
            Nurse nurse_vl1 =new Nurse(staff_nurse_vl1);
            nurseRepository.save(nurse_vl1);

        //endregion

        //region Appointments
        Calendar start_time = Calendar.getInstance();
        start_time.set(2020,Calendar.MARCH,25,8,0,0);
        start_time.set(Calendar.MILLISECOND,0);
        Calendar final_app = Calendar.getInstance();
        final_app.set(2020,Calendar.MARCH,25,15,30,0);
        final_app.set(Calendar.MILLISECOND,0);
        while(start_time.compareTo(final_app)<0){
            Calendar endTime= (Calendar) start_time.clone();
            endTime.add(Calendar.MINUTE,30);
            ArrayList<AppointmentType> at_list= new ArrayList<AppointmentType>();
            at_list.addAll(doctor1.getSpecializations());
            AppointmentType temp_at= at_list.get(ThreadLocalRandom.current().nextInt(0, at_list.size()));
            Appointment ap_req1 = new Appointment(start_time.getTime(),endTime.getTime(), temp_at, patient1, null, doctor1);
            ap_req1.setAppState(AppStateEnum.APPROVED);
            appointmentRepository.save(ap_req1);
            start_time.add(Calendar.MINUTE, 30);
            List<Room> list = new ArrayList<Room>(clinicns.getRooms());
            Room ap_room = list.get(0);
            clinicns.getRooms().remove(ap_room);
            ap_room.addAppointment(ap_req1);
            ap_room=roomRepository.save(ap_room);
            clinicns.getRooms().add(ap_room);
            ap_req1.addRoom(ap_room);
            appointmentRepository.save(ap_req1);
            doctor1.addAppointment(ap_req1);
        }
        doctorRepository.save(doctor1);
        //endregion

        //region Recipe
            Recipe recipe1 = new Recipe(drug1,nurse_ns1,patient1);
            patient1.getMedicalRecord().addRecipe(recipe1);
            patient1 =patientRepository.save(patient1);
            List<Recipe> recipes = new ArrayList<Recipe>(patient1.getMedicalRecord().getRecipes());
            nurse_ns1.addRecipe(recipes.get(recipes.size()-1));
            nurse_ns1 = nurseRepository.save(nurse_ns1);

            Recipe recipe2 = new Recipe(drug4,nurse_ns1,patient1);
            patient1.getMedicalRecord().addRecipe(recipe2);
            patient1=patientRepository.save(patient1);
            recipes = new ArrayList<Recipe>(patient1.getMedicalRecord().getRecipes());
            nurse_ns1.addRecipe(recipes.get(recipes.size()-1));
            nurseRepository.save(nurse_ns1);
        //endregion
*/
    }
}
