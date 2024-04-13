package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Response.ExaminationServiceResponse;

import java.util.Set;

public interface IExaminationService {
    Set<ExaminationServiceResponse> getExaminationService(String serviceTypeName) throws Exception;
}
