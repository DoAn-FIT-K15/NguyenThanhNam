package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Response.Services2ScheduleResponse;

public interface IScheduleService {
    Services2ScheduleResponse getScheduleByExamName(String examName) throws Exception;
}
