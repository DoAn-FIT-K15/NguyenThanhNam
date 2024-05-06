package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Response.ScheduleResponse;
import org.example.doantotnghiep.Payload.Response.Services2ScheduleResponse;
import org.example.doantotnghiep.Repository.ExaminationServiceRepo;
import org.example.doantotnghiep.Repository.ScheduleRepo;
import org.example.doantotnghiep.Repository.ServiceTypeRepo;
import org.example.doantotnghiep.Repository.SpecialistRepo;
import org.example.doantotnghiep.Service.iservice.IScheduleService;
import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;



@Service
public class ScheduleService implements IScheduleService {
    @Autowired
    private SpecialistRepo specialistRepo;
    @Autowired
    private ScheduleRepo scheduleRepo;
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;
    @Override
    public Services2ScheduleResponse getScheduleByExamName(String examName) throws Exception {
        ExaminationService examinationService = examinationServiceRepo.findByName(examName);

        Services2ScheduleResponse response = new Services2ScheduleResponse();
        response.setAvata(examinationService.getImg());
        response.setName(examinationService.getName());
        response.setExaminationServiceName(examinationService.getName());
        response.setShortenedName(examinationService.getShortenedName());
        response.setPrice(examinationService.getPrice());
        response.setDescription(examinationService.getDescription());
        response.setServiceTypeName(serviceTypeRepo.findById(examinationService.getServiceType().getId()).get().getName());
        Set<Schedule> schedules = scheduleRepo.findByExaminationService(examinationService);
        Set<ScheduleResponse> scheduleResponses = new TreeSet<>(Comparator.comparing(this::getStartAtFormatted));
        boolean clinicInfoSet = false;
        for (Schedule schedule : schedules) {
            ScheduleResponse scheduleResponse = new ScheduleResponse();
            scheduleResponse.setDayofWeek(getDayOfWeek(schedule.getStartAt()));
            scheduleResponse.setDate(getDate(schedule.getStartAt()));
            scheduleResponse.setTime(formatTime(schedule.getStartAt(), schedule.getEndAt()));
            scheduleResponse.setEmpty(schedule.isEmpty());
            if (!clinicInfoSet) {
                response.setClinicName(schedule.getClinic().getName());
                response.setAddressClinic(schedule.getClinic().getAddress());
                clinicInfoSet = true;
            }
            scheduleResponses.add(scheduleResponse);
        }
        response.setScheduleResponses(scheduleResponses);
        return response;
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
