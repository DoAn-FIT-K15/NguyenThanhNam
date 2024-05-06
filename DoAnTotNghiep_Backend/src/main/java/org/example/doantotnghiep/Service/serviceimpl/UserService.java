package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.user_request.CreateDoctorRequest;
import org.example.doantotnghiep.Payload.Response.DoctorResponse;
import org.example.doantotnghiep.Payload.Response.ScheduleResponse;
import org.example.doantotnghiep.Repository.*;
import org.example.doantotnghiep.Service.iservice.IUserService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.*;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private SpecialistRepo specialistRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;
    private final BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();

    @Override
    public String changePassword(ChangePasswordRequest changePasswordRequest) throws DataNotFoundException {
        User user = userRepo.findByEmail(changePasswordRequest.getEmail()).orElse(null);
        if(user == null){
            throw new DataNotFoundException(MessageKeys.USER_DOES_NOT_EXIST);
        }
        String mkCu = changePasswordRequest.getOldPassword();
//        BCryptPasswordEncoder bCrypt = new BCryptPasswordEncoder();
        if(!bCrypt.matches(mkCu, user.getPassword())){
            return "Mật khẩu cũ không chính xác";
        }
        String mkMoi = changePasswordRequest.getNewPassword();
        if(!mkMoi.equals(changePasswordRequest.getRetypePassword())){
            return "Xác nhận mật khẩu không đúng";
        }
        user.setPassword(bCrypt.encode(mkMoi));
        userRepo.save(user);
        return "Thay đổi mật khẩu thành công ";
    }

    @Override
    public User createDoctor(CreateDoctorRequest createDoctorRequest) throws Exception {
        if(userRepo.existsByEmail(createDoctorRequest.getEmail())){
            throw new DataIntegrityViolationException(MessageKeys.DOCTOR_ALREADY_EXISTS);
        }
        User doctor = new User();
        doctor.setName(createDoctorRequest.getName());
        doctor.setEmail(createDoctorRequest.getEmail());
        doctor.setPassword(bCrypt.encode(createDoctorRequest.getPassword()));
        doctor.setPosition(createDoctorRequest.getPosition());
        doctor.setGender(createDoctorRequest.getGender());
        doctor.setActive(true);
        doctor.setRole(roleRepo.findById(2).orElse(null));
        doctor.setSpecialist(specialistRepo.findByName(createDoctorRequest.getSpecialistName()));
        doctor.setExaminationService(examinationServiceRepo.findByName(createDoctorRequest.getExaminationServicename()));
        doctor.setAvata(createDoctorRequest.getAvata());
        doctor.setDescription(createDoctorRequest.getDescription());
        doctor.setCreatedAt(LocalDateTime.now());
        userRepo.save(doctor);
        return doctor;
    }

    @Override
    public Set<DoctorResponse> getDoctorbySpecialist(String name) throws Exception {
        Specialist specialist = specialistRepo.findByName(name);
        Set<User> doctors = new HashSet<>();
        String examinationServicename = "";
        String serviceTypeName = "";

        if (specialist != null) {
            doctors = userRepo.findBySpecialist(specialist);
            examinationServicename=specialist.getName();
            ServiceType serviceType = serviceTypeRepo.findById(specialist.getIdServiceType()).get();
            serviceTypeName= serviceType.getName();
        }
        else {
            ExaminationService examinationService = examinationServiceRepo.findByName(name);
            doctors = userRepo.findByExaminationService(examinationService);
            examinationServicename=examinationService.getName();
            ServiceType serviceType = serviceTypeRepo.findById(examinationService.getServiceType().getId()).get();
            serviceTypeName= serviceType.getName();
        }

        String finalExaminationServicename = examinationServicename;
        String finalServiceTypeName = serviceTypeName;
        return doctors.stream()
                .map(doctor -> {
                    DoctorResponse doctorResponse = new DoctorResponse();
                    doctorResponse.setExaminationServicename(finalExaminationServicename);
                    doctorResponse.setServiceTypeName(finalServiceTypeName);
                    doctorResponse.setAvata(doctor.getAvata());
                    doctorResponse.setName(doctor.getName());
                    doctorResponse.setPosition(doctor.getPosition());
                    doctorResponse.setDescription(doctor.getDescription());
                    doctorResponse.setPrice(doctor.getPrice());
                    Set<Schedule> schedules = scheduleRepo.findByDoctor(doctor);
                    Set<ScheduleResponse> scheduleResponses = new TreeSet<>(Comparator.comparing(this::getStartAtFormatted));
                    boolean clinicInfoSet = false;
                    for (Schedule schedule : schedules) {
                        ScheduleResponse scheduleResponse = new ScheduleResponse();
                        scheduleResponse.setDayofWeek(getDayOfWeek(schedule.getStartAt()));
                        scheduleResponse.setDate(getDate(schedule.getStartAt()));
                        scheduleResponse.setTime(formatTime(schedule.getStartAt(), schedule.getEndAt()));
                        scheduleResponse.setEmpty(schedule.isEmpty());
                        if (!clinicInfoSet) {
                            doctorResponse.setClinicName(schedule.getClinic().getName());
                            doctorResponse.setAddressClinic(schedule.getClinic().getAddress());
                            clinicInfoSet = true;
                        }
                        scheduleResponses.add(scheduleResponse);
                    }
                    doctorResponse.setScheduleResponses(scheduleResponses);
                    return doctorResponse;
                })
                .collect(Collectors.toSet());
    }



    public static String getDayOfWeek(LocalDateTime dateTime) {
        DayOfWeek dayOfWeek = dateTime.getDayOfWeek();
        return convertDayOfWeekToString(dayOfWeek);
    }

    public static String convertDayOfWeekToString(DayOfWeek dayOfWeek) {
        switch (dayOfWeek) {
            case MONDAY:
                return "Thứ 2";
            case TUESDAY:
                return "Thứ 3";
            case WEDNESDAY:
                return "Thứ 4";
            case THURSDAY:
                return "Thứ 5";
            case FRIDAY:
                return "Thứ 6";
            case SATURDAY:
                return "Thứ 7";
            case SUNDAY:
                return "Chủ nhật";
            default:
                return ""; // Trường hợp không xác định
        }
    }

    public static String getDate(LocalDateTime dateTime) {
        int day = dateTime.getDayOfMonth();
        int month = dateTime.getMonthValue();
        String dayStr = (day < 10) ? "0" + day : String.valueOf(day);
        String monthStr = (month < 10) ? "0" + month : String.valueOf(month);
        return dayStr + "/" + monthStr + "/" + dateTime.getYear();
    }

    public static String formatTime(LocalDateTime startAt, LocalDateTime endAt) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        String formattedStart = startAt.format(formatter);
        String formattedEnd = endAt.format(formatter);
        return formattedStart + "-" + formattedEnd;
    }
    // Định nghĩa hàm để lấy thời gian bắt đầu đã được định dạng
    private String getStartAtFormatted(ScheduleResponse scheduleResponse) {
        return scheduleResponse.getTime().split("-")[0];
    }
}
