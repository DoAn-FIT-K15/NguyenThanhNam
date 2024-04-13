package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Payload.Request.auth_request.ChangePasswordRequest;
import org.example.doantotnghiep.Payload.Request.user_request.CreateDoctorRequest;
import org.example.doantotnghiep.Service.serviceimpl.UserService;
import org.example.doantotnghiep.exceptions.DataIntegrityViolationException;
import org.example.doantotnghiep.exceptions.DataNotFoundException;
import org.example.doantotnghiep.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @PutMapping("/changePassword")
    public ResponseEntity<?> changePassword(@Valid @RequestBody ChangePasswordRequest changePasswordRequest) throws Exception {
        try{
            String result =  userService.changePassword(changePasswordRequest);
            return ResponseEntity.ok().body(result);

        }catch (DataNotFoundException e){
            throw new DataNotFoundException(e.getMessage());
        }
    }

    @PostMapping("/createDoctor")
    public ResponseEntity<?> createDoctor(@Valid @RequestBody CreateDoctorRequest createDoctorRequest) throws Exception{
        try {
            User doctor = userService.createDoctor(createDoctorRequest);
            return ResponseEntity.ok().body(doctor);

        }catch (DataIntegrityViolationException ex){
            return ResponseEntity.badRequest().body(ex.getMessage());
        }
    }

    @GetMapping("/getDoctorbySpecialist")
    public ResponseEntity<?> getDoctorBySpecialist(@Valid @RequestParam String specialistName) throws Exception {
        try {
            return ResponseEntity.ok().body(userService.getDoctorbySpecialist(specialistName));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
