package com.example.employee_api.medicalrecord.repository;

import com.example.employee_api.medicalrecord.model.entity.MedicalRecord;
import com.example.employee_api.patient.model.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MedicalRecordRepository extends JpaRepository<MedicalRecord, Long> {

    Optional<MedicalRecord> findByPatientAndStatus(Patient patient, MedicalRecord.Status status);
}
