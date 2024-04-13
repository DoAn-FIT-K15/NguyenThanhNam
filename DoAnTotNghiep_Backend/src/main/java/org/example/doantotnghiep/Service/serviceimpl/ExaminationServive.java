package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Response.ExaminationServiceResponse;
import org.example.doantotnghiep.Repository.ExaminationServiceRepo;
import org.example.doantotnghiep.Repository.ServiceTypeRepo;
import org.example.doantotnghiep.Service.iservice.IExaminationService;
import org.example.doantotnghiep.model.ExaminationService;
import org.example.doantotnghiep.model.ServiceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ExaminationServive implements IExaminationService {
    @Autowired
    private ServiceTypeRepo serviceTypeRepo;
    @Autowired
    private ExaminationServiceRepo examinationServiceRepo;
    @Override
    public Set<ExaminationServiceResponse> getExaminationService(String serviceTypeName) throws Exception {
        ServiceType serviceType = serviceTypeRepo.findByName(serviceTypeName);
        Set<ExaminationService> examinationServices = examinationServiceRepo.findByServiceType(serviceType);

        return examinationServices.stream()
                .map(examinationService -> {
                    ExaminationServiceResponse examinationServiceResponse = new ExaminationServiceResponse();
                    examinationServiceResponse.setId(examinationService.getId());
                    examinationServiceResponse.setImg(examinationService.getImg());
                    examinationServiceResponse.setName(examinationService.getName());
                    return examinationServiceResponse;
                })
                .collect(Collectors.toSet());
    }

}
