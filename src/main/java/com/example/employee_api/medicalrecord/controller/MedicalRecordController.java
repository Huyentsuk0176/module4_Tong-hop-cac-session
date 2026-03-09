package com.example.employee_api.medicalrecord.controller;

import com.example.employee_api.medicalrecord.model.dto.MedicalRecordRequest;
import com.example.employee_api.medicalrecord.service.MedicalRecordService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/medical-records")
public class MedicalRecordController {

    private final MedicalRecordService medicalRecordService;

    public MedicalRecordController(MedicalRecordService medicalRecordService) {
        this.medicalRecordService = medicalRecordService;
    }

    @PostMapping
    public String createMedicalRecord(@RequestBody MedicalRecordRequest request) {
        return medicalRecordService.createMedicalRecord(request);
    }
}