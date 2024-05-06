package org.example.doantotnghiep.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.doantotnghiep.Service.serviceimpl.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api.prefix}/user")
@RequiredArgsConstructor
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


    @GetMapping("/getScheduleByExamName")
    public ResponseEntity<?> getDoctorBySpecialist(@Valid @RequestParam String name) throws Exception {
        try {
            return ResponseEntity.ok().body(scheduleService.getScheduleByExamName(name));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}
