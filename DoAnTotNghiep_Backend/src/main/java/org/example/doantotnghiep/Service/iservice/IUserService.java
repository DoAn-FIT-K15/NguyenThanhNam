package org.example.doantotnghiep.Service.iservice;

import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.user_request.CreateDoctorRequest;
import org.example.doantotnghiep.Payload.Response.DoctorResponse;
import org.example.doantotnghiep.model.User;

import java.util.Set;

public interface IUserService {

    String changePassword(ChangePasswordRequest changePasswordRequest) throws Exception;

    User createDoctor(CreateDoctorRequest createDoctorRequest) throws Exception;

    Set<DoctorResponse> getDoctorbySpecialist(String specialistName) throws Exception;
}
