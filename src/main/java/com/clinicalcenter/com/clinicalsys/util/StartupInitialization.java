package com.clinicalcenter.com.clinicalsys.util;

import com.clinicalcenter.com.clinicalsys.model.*;
import com.clinicalcenter.com.clinicalsys.model.enumeration.AppStateEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoleEnum;
import com.clinicalcenter.com.clinicalsys.model.enumeration.RoomType;
import com.clinicalcenter.com.clinicalsys.repository.*;
import com.clinicalcenter.com.clinicalsys.services.NotifyAdminsServis;
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
    SurgeryRepository surgeryRepository;
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
    @Autowired
    VacationRepository vacationRepository;
    @Autowired
    private NotifyAdminsServis notifyAdminsServis;

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
        Diagnose diagnose3 = new Diagnose("Temperatura",
                "Fusce pharetra sem nisl, non gravida neque vulputate in. Praesent nec augue odio amet.");
        diagnoseRepository.save(diagnose3);
        Diagnose diagnose4 = new Diagnose("Upala pluca",
                "Vivamus mattis pellentesque augue nec nullam. ");
        diagnoseRepository.save(diagnose4);
        Diagnose diagnose5 = new Diagnose("Visok pritisak",
                "Pellentesque faucibus sapien velit, facilisis consectetur enim egestas et. ");
        diagnoseRepository.save(diagnose5);
        Diagnose diagnose6 = new Diagnose("Nizak secer",
                "Morbi venenatis sapien et dolor pellentesque condimentum. Mauris quis eros sem.");
        diagnoseRepository.save(diagnose6);
        Diagnose diagnose7 = new Diagnose("Glavobolja ",
                " Aliquam aliquet tellus nec sapien efficitur commodo. Aenean nibh ante, ornare eu mi eu, " +
                        "tristique commodo lorem.");
        diagnoseRepository.save(diagnose7);
        ArrayList<Diagnose> diagnoses= new ArrayList<>();
        diagnoses.add(diagnose1);diagnoses.add(diagnose2);diagnoses.add(diagnose3);diagnoses.add(diagnose4);
        diagnoses.add(diagnose5);diagnoses.add(diagnose6);diagnoses.add(diagnose7);
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
        patient1.setAdminConfirmed(Boolean.TRUE);
        patient1.setActive(Boolean.TRUE);
        patientRepository.save(patient1);
        User user_patient2 = new User("drugipacijent@php.net","Boris","Brejcha",
                "78596789","88529 Fairfield Parkway","Subotica","Serbia",
                "(682) 9425368","9598795971511");
        Patient patient2 = new Patient(user_patient2);
        patient1.setAdminConfirmed(Boolean.TRUE);
        patient1.setActive(Boolean.TRUE);
        patientRepository.save(patient2);
        User user_patient3 = new User("instapatient@java.net","Senidah","The Third",
                "120565","88529 Fairfield Parkway","Szeged","Hungary",
                "(682) 9449668","9598205101511");
        Patient patient3 = new Patient(user_patient3);
        patient1.setAdminConfirmed(Boolean.TRUE);
        patient1.setActive(Boolean.TRUE);
        patientRepository.save(patient3);
        User user_patient4 = new User("finalpatient@test.co","Yo","Yo",
                "007565","88529 Fairfield Parkway","Pecs","Hungary",
                "(682) 9489668","9598205970311");
        Patient patient4 = new Patient(user_patient4);
        patient1.setActive(Boolean.TRUE);
        patientRepository.save(patient4);
        ArrayList<Patient> patients = new ArrayList<>();
        patients.add(patient1);patients.add(patient2);patients.add(patient3);patients.add(patient4);
        //endregion

        //region ClinicAdmin
        User user_cadminbg = new User("wdeekes5@tmall.com","Whittaker","Deekes",
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
        ArrayList<Doctor> doctors = new ArrayList<>();
        User user_doctor_1 = new User("jpavlovic0@google.com.au","Justus","Pavlovic",
                "557369","810 Cherokee Lane", "Kuštilj","Srbija",
                "(682) 9409237","8657870655830");
        user_doctor_1.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_1 = new Staff(user_doctor_1, clinicbg);
        Doctor doctor1 = new Doctor(staff_doctor_1,8.18);
        doctor1.addSpecialisation(appointmentType1);
        doctor1.addSpecialisation(appointmentType3);
        doctor1.addSpecialisation(appointmentType10);
        doctor1 = doctorRepository.save(doctor1);
        doctors.add(doctor1);
        clinicbg.addNewAppTypePriceDiscount(appointmentType1,456.0,1.0);
        clinicbg.addNewAppTypePriceDiscount(appointmentType3,590.0,0.95);
        clinicbg.addNewAppTypePriceDiscount(appointmentType10,1200.0,0.8);
        clinicbg = clinicRespository.save(clinicbg);

        User user_doctor_2 = new User("jmingardo1@howstuffworks.com","Judith","Mingardo",
                "638473","33 7th Crossing","Gyor","Madjarska",
                "(666) 6994851","1715377197364");
        user_doctor_2.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_2 = new Staff(user_doctor_2, clinicbg);
        Doctor doctor2 = new Doctor(staff_doctor_2,9.58);
        doctor2.addSpecialisation(appointmentType2);
        doctor2.addSpecialisation(appointmentType6);
        doctor2.addSpecialisation(appointmentType8);
        doctor2.addSpecialisation(appointmentType7);
        doctor2 = doctorRepository.save(doctor2);
        doctors.add(doctor2);
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
        Doctor doctor3 = new Doctor(staff_doctor_3,6.12);
        doctor3.addSpecialisation(appointmentType1);
        doctor3.addSpecialisation(appointmentType4);
        doctor3.addSpecialisation(appointmentType8);
        doctor3.addSpecialisation(appointmentType6);
        doctor3 = doctorRepository.save(doctor3);
        doctors.add(doctor3);
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
        Doctor doctor4 = new Doctor(staff_doctor_4,9.16);
        doctor4.addSpecialisation(appointmentType5);
        doctor4.addSpecialisation(appointmentType10);
        doctor4 = doctorRepository.save(doctor4);
        doctors.add(doctor4);
        clinicns.addNewAppTypePriceDiscount(appointmentType5,920.0,1.0);
        clinicns.addNewAppTypePriceDiscount(appointmentType10,2200.0,0.7);
        clinicns = clinicRespository.save(clinicns);

        User user_doctor_5 = new User("kbalbeck9@java.com","Karyn","Balbeck",
                "695696","8 Ilene Junction","Budapest","Hungary",
                "(587) 8660314","8518068206648");
        user_doctor_5.setRole(RoleEnum.DOCTOR);
        Staff staff_doctor_5 = new Staff(user_doctor_5, clinicsu);
        Doctor doctor5 = new Doctor(staff_doctor_5,5.56);
        doctor5.addSpecialisation(appointmentType1);
        doctor5.addSpecialisation(appointmentType4);
        doctor5.addSpecialisation(appointmentType8);
        doctor5.addSpecialisation(appointmentType3);
        doctor5 = doctorRepository.save(doctor5);
        doctors.add(doctor5);
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
        Doctor doctor6 = new Doctor(staff_doctor_6,7.14);
        doctor6.addSpecialisation(appointmentType6);
        doctor6 = doctorRepository.save(doctor6);
        doctors.add(doctor6);
        clinicvl.addNewAppTypePriceDiscount(appointmentType6, 860.0,0.9);
        clinicvl = clinicRespository.save(clinicvl);
        //endregion

        //region Nurses
            User user_nurse_ns1 = new User("rkitchinghamd@salon.com","Robinette","Kitchingham",
                "272851","1715 Boyd Place","Nova Pazova","Serbia",
                "(687) 2189973","8211024836776");
            Staff staff_nurse_ns1 = new Staff(user_nurse_ns1,clinicns);
            Nurse nurse_ns1 =new Nurse(staff_nurse_ns1);
            nurse_ns1=nurseRepository.save(nurse_ns1);

            User user_nurse_ns2 = new User("nije_bitno@mejl.com","Matt","Parker",
                    "514265","1715 Boyd Place","Nova Pazova","Serbia",
                    "(687) 2189973","8211024836789");
            Staff staff_nurse_ns2 = new Staff(user_nurse_ns2,clinicns);
            Nurse nurse_ns2 =new Nurse(staff_nurse_ns2);
            nurse_ns2=nurseRepository.save(nurse_ns2);

            User user_nurse_bg1 = new User("dbishopp4@wikispaces.com","Delcine","Bishopp",
                    "785391","1810 Michigan Way", "Balatun","Bosnia and Herzegovina",
                    "(192) 8186705","6771172485802");
            Staff staff_nurse_bg1 = new Staff(user_nurse_bg1,clinicbg);
            Nurse nurse_bg1 =new Nurse(staff_nurse_bg1);
            nurse_bg1=nurseRepository.save(nurse_bg1);

            User user_nurse_bg2 = new User("testtest@wikispaces.com","Brow","Rice",
                    "782891","1810 Michigan Way", "Balatun","Bosnia and Herzegovina",
                    "(192) 8186705","6771178985802");
            Staff staff_nurse_bg2 = new Staff(user_nurse_bg2,clinicbg);
            Nurse nurse_bg2 =new Nurse(staff_nurse_bg2);
            nurse_bg2=nurseRepository.save(nurse_bg2);

            User user_nurse_su1 = new User("sscholte5@examiner.com","Sydney","Scholte",
                    "401257","48 Hoard Hill","Lipci","Montenegro",
                    "(286) 9748369","3636790947925");
            Staff staff_nurse_su1 = new Staff(user_nurse_su1,clinicsu);
            Nurse nurse_su1 =new Nurse(staff_nurse_su1);
            nurse_su1=nurseRepository.save(nurse_su1);

            User user_nurse_vl1 = new User("nurse@nrs.com","Robinette","Kitchingham",
                    "123123","1715 Boyd Place","Nova Pazova","Serbia",
                    "(687) 2189973","8211024836776");
            Staff staff_nurse_vl1 = new Staff(user_nurse_vl1,clinicvl);
            Nurse nurse_vl1 =new Nurse(staff_nurse_vl1);
            nurse_vl1=nurseRepository.save(nurse_vl1);
            ArrayList<Nurse> nurses= new ArrayList<>();
            nurses.add(nurse_bg1);nurses.add(nurse_bg2);nurses.add(nurse_ns1);nurses.add(nurse_ns2);
            nurses.add(nurse_su1);nurses.add(nurse_vl1);
        //endregion



        //region Appointments/Vacations/Surgeries
        for (Doctor doctor : doctors) {
            //Vaccation
            for(int i =0;i<2;i++) {
                int v_month = ThreadLocalRandom.current().nextInt(0, 3);
                int v_day = ThreadLocalRandom.current().nextInt(1, 29);
                Calendar v_start_time = Calendar.getInstance();
                v_start_time.set(2020, v_month, v_day, 0, 0, 0);
                v_start_time.set(Calendar.MILLISECOND, 0);

                int vacation_length = ThreadLocalRandom.current().nextInt(1, 8);
                Calendar v_end_time = (Calendar) v_start_time.clone();
                v_end_time.add(Calendar.DAY_OF_YEAR, vacation_length);

                String v_type = (ThreadLocalRandom.current().nextInt(0, 2) == 0) ? "HOLIDAY" : "ABSENCE";
                int accepted_randomise = ThreadLocalRandom.current().nextInt(0, 3);
                Boolean accepted = null;
                if (accepted_randomise == 0) accepted = true;
                if (accepted_randomise == 1) accepted = false;
                if(v_end_time.getTime().before(new Date())) accepted=true;
                VacationRequest vacation = new VacationRequest(v_type, v_start_time.getTime(), v_end_time.getTime(), accepted);
                vacation=vacationRepository.save(vacation);
                if(accepted!=null&&accepted==true) {
                    doctor.getVacReq().add(vacation);
                    doctor = doctorRepository.save(doctor);
                }
                if (accepted == null) {
                    Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(doctor.getEmail());
                    for (ClinicAdmin admin : clinicAdmins) {
                        admin.getVacations_to_process().add(vacation);
                        admin = clinicAdminRepository.save(admin);
                        //notifyAdminsServis.newRequestNotification(admin,false);
                    }
                }
            }
            //Apppointments
            int iterations=ThreadLocalRandom.current().nextInt(15, 30 + 1);
            for(int i=0;i<iterations;i++) {
                int month=ThreadLocalRandom.current().nextInt(1, 3);
                int day=ThreadLocalRandom.current().nextInt(1, 29);
                int hour=ThreadLocalRandom.current().nextInt(8,15);
                int minutes=(ThreadLocalRandom.current().nextInt(0,2)==0)?0:30;
                Calendar start_time = Calendar.getInstance();
                start_time.set(2020, month, day, hour, minutes, 0);
                start_time.set(Calendar.MILLISECOND, 0);

                Calendar end_time = (Calendar) start_time.clone();
                end_time.add(Calendar.MINUTE,30);

                if(!doctor.checkIfAppFree(start_time,end_time)) continue;

                Patient app_patient = patients.get(ThreadLocalRandom.current().nextInt(0, patients.size()));
                ArrayList<AppointmentType> at_list = new ArrayList<AppointmentType>();
                at_list.addAll(doctor.getSpecializations());
                AppointmentType temp_at = at_list.get(ThreadLocalRandom.current().nextInt(0, at_list.size()));
                Appointment ap_req1 = new Appointment(start_time.getTime(), end_time.getTime(), temp_at, app_patient,
                        null, doctor);
                if(ap_req1.getEndTime().before(new Date())){
                    ap_req1.setAppState(AppStateEnum.FINISHED);
                }else {
                    AppStateEnum appStateEnum = (ThreadLocalRandom.current().nextInt(0, 2) == 0) ?
                            AppStateEnum.APPROVED : AppStateEnum.REQUESTED;
                    ap_req1.setAppState(appStateEnum);
                }
                ap_req1=appointmentRepository.save(ap_req1);
                if(ap_req1.getAppState()==AppStateEnum.FINISHED){
                    doctor.addAppointment(ap_req1);
                }
                if(ap_req1.getAppState()==AppStateEnum.APPROVED) {
                    List<Room> list = new ArrayList<Room>(doctor.getClinic().getRooms());
                    Room ap_room = list.get(ThreadLocalRandom.current().nextInt(0,list.size()));
                    doctor.getClinic().getRooms().remove(ap_room);
                    ap_room.addAppointment(ap_req1);
                    Set<Nurse> retVal = nurseRepository.findByClinicName(doctor.getClinic().getClinicName());
                    List<Nurse> clinic_nurses = new ArrayList<Nurse>();
                    clinic_nurses.addAll(retVal);
                    Nurse temp_nurse = clinic_nurses.get(ThreadLocalRandom.current().nextInt(0,clinic_nurses.size()));
                    temp_nurse.getAppointments().add(ap_req1);
                    nurses.remove(temp_nurse);
                    nurses.add(nurseRepository.save(temp_nurse));
                    //mozda room treba da sacuvamo preko klinike, a ne ovako direktno
                    ap_room = roomRepository.save(ap_room);
                    doctor.getClinic().getRooms().add(ap_room);
                    ap_req1.addRoom(ap_room);
                    ap_req1=appointmentRepository.save(ap_req1);
                    app_patient.getFuture_appointments().add(ap_req1);
                    patients.remove(app_patient);
                    patients.add(patientRepository.save(app_patient));
                    doctor.addAppointment(ap_req1);
                }else if(ap_req1.getAppState()==AppStateEnum.REQUESTED){
                    Set<ClinicAdmin> clinicAdmins = clinicAdminRepository.getByDoctorEmail(doctor.getEmail());
                    for (ClinicAdmin admin:clinicAdmins){
                        admin.getAppointments_to_process().add(ap_req1);
                        admin = clinicAdminRepository.save(admin);
                    }
                }

            }
            //Surgery
            /*iterations=ThreadLocalRandom.current().nextInt(15, 30 + 1);
            for(int i=0;i<iterations;i++) {
                int month=ThreadLocalRandom.current().nextInt(1, 3);
                int day=ThreadLocalRandom.current().nextInt(1, 29);
                int hour=ThreadLocalRandom.current().nextInt(8,15);
                int minutes=(ThreadLocalRandom.current().nextInt(0,2)==0)?0:30;
                Calendar start_time = Calendar.getInstance();
                start_time.set(2020, month, day, hour, minutes, 0);
                start_time.set(Calendar.MILLISECOND, 0);

                Calendar end_time = (Calendar) start_time.clone();
                end_time.add(Calendar.MINUTE,30);

                if(!doctor.checkIfAppFree(start_time,end_time)) continue;

                Patient app_patient = patients.get(ThreadLocalRandom.current().nextInt(0, patients.size()));

                Boolean activated= (ThreadLocalRandom.current().nextInt(0, 2) == 0) ? true:false;
                Surgery surgery = new Surgery(start_time.getTime(), end_time.getTime(),app_patient,doctor,null,activated);
                surgery=surgeryRepository.save(surgery);
                if(activated!=null&&activated==Boolean.TRUE){
                    List<Room> list = new ArrayList<Room>(doctor.getClinic().getRooms());
                    Room ap_room = list.get(ThreadLocalRandom.current().nextInt(0,list.size()));
                    doctor.getClinic().getRooms().remove(ap_room);
                    ap_room.set(surgery);
                    Set<Nurse> retVal = nurseRepository.findByClinicName(doctor.getClinic().getClinicName());
                    List<Nurse> clinic_nurses = new ArrayList<Nurse>();
                    clinic_nurses.addAll(retVal);
                    Nurse temp_nurse = clinic_nurses.get(ThreadLocalRandom.current().nextInt(0,clinic_nurses.size()));
                    temp_nurse.getAppointments().add(ap_req1);
                    nurses.remove(temp_nurse);
                    nurses.add(nurseRepository.save(temp_nurse));
                    //mozda room treba da sacuvamo preko klinike, a ne ovako direktno
                    ap_room = roomRepository.save(ap_room);
                    doctor.getClinic().getRooms().add(ap_room);
                    ap_req1.addRoom(ap_room);
                    ap_req1=appointmentRepository.save(ap_req1);
                    app_patient.getFuture_appointments().add(ap_req1);
                    patients.remove(app_patient);
                    patients.add(patientRepository.save(app_patient));
                    doctor.addAppointment(ap_req1);
                }
            }

            }*/
            doctorRepository.save(doctor);
        }
        //endregion


        //region Recipe
        for(int i=0; i<40 ;i++) {
            Drug drug = drugs.get(ThreadLocalRandom.current().nextInt(0,drugs.size()));
            Nurse nurse = nurses.get(ThreadLocalRandom.current().nextInt(0,nurses.size()));

            Patient patient = patients.get(ThreadLocalRandom.current().nextInt(0,patients.size()));
            patients.remove(patient);

            Recipe recipe = new Recipe(drug, nurse, patient);
            boolean validated = (ThreadLocalRandom.current().nextInt(0, 2) == 0) ? true : false;

            recipe.setValidate(validated);

            patient.getMedicalRecord().addRecipe(recipe);
            patients.add(patientRepository.save(patient));
        }
        //endregion
    /*
        //region Adding diagnoses to patients
        for(Patient patient: patients) {
            int iterations=ThreadLocalRandom.current().nextInt(1, 3);
            for(int i = 0 ;i<iterations;i++){
                Long diagnosesId=diagnoses.get(ThreadLocalRandom.current().nextInt(0,diagnoses.size())).getId();
                Long medicalRecId = patient.getMedicalRecord().getId();
                patientRepository.addDiagnoses(medicalRecId,diagnosesId);
            }

        }
        */

        //endregion
    }
}
