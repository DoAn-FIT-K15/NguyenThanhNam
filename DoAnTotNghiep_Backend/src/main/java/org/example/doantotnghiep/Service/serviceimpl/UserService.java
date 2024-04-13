package org.example.doantotnghiep.Service.serviceimpl;

import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.user_request.CreateDoctorRequest;
import org.example.doantotnghiep.Payload.Response.DoctorResponse;
import org.example.doantotnghiep.Payload.Response.ExaminationServiceResponse;
import org.example.doantotnghiep.Repository.RoleRepo;
import org.example.doantotnghiep.Repository.SpecialistRepo;
import org.example.doantotnghiep.Repository.UserRepo;
import org.example.doantotnghiep.Service.iservice.IUserService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.Specialist;
import org.example.doantotnghiep.model.User;
import org.example.doantotnghiep.utils.MessageKeys;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepo userRepo;
    @Autowired
    private RoleRepo roleRepo;
    @Autowired
    private SpecialistRepo specialistRepo;

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
        doctor.setAvata(createDoctorRequest.getAvata());
        doctor.setDescription(createDoctorRequest.getDescription());
        doctor.setCreatedAt(LocalDateTime.now());
        userRepo.save(doctor);
        return doctor;
    }

    @Override
    public Set<DoctorResponse> getDoctorbySpecialist(String specialistName) throws Exception {
        Specialist specialist = specialistRepo.findByName(specialistName);
        Set<User> doctors = userRepo.findBySpecialist(specialist);

        return doctors.stream()
                .map(doctor -> {
                    DoctorResponse doctorResponse = new DoctorResponse();
                    doctorResponse.setAvata(doctor.getAvata());
                    doctorResponse.setName(doctor.getName());
                    doctorResponse.setPosition(doctor.getPosition());
                    doctorResponse.setDescription(doctor.getDescription());
                    doctorResponse.setPrice(doctor.getPrice());
                    return doctorResponse;
                })
                .collect(Collectors.toSet());
    }
}
