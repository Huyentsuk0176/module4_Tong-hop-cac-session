package com.example.employee_api.medicalrecord.service;

import com.example.employee_api.doctor.model.entity.Doctor;
import com.example.employee_api.doctor.repository.DoctorRepository;
import com.example.employee_api.medicalrecord.model.dto.MedicalRecordRequest;
import com.example.employee_api.medicalrecord.model.entity.MedicalRecord;
import com.example.employee_api.medicalrecord.repository.MedicalRecordRepository;
import com.example.employee_api.patient.model.entity.Patient;
import com.example.employee_api.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

@Service
public class MedicalRecordService {

    private final MedicalRecordRepository medicalRecordRepository;
    private final DoctorRepository doctorRepository;
    private final PatientRepository patientRepository;

    public MedicalRecordService(
            MedicalRecordRepository medicalRecordRepository,
            DoctorRepository doctorRepository,
            PatientRepository patientRepository) {

        this.medicalRecordRepository = medicalRecordRepository;
        this.doctorRepository = doctorRepository;
        this.patientRepository = patientRepository;
    }

    public String createMedicalRecord(MedicalRecordRequest request) {

        Doctor doctor = doctorRepository.findById(request.getDoctorId())
                .orElseThrow(() -> new RuntimeException("Doctor not found"));

        Patient patient = patientRepository.findById(request.getPatientId())
                .orElseThrow(() -> new RuntimeException("Patient not found"));

        // kiểm tra hồ sơ đang PROCESSING
        boolean hasProcessing =
                medicalRecordRepository
                        .findByPatientAndStatus(patient, MedicalRecord.Status.PROCESSING)
                        .isPresent();

        if (hasProcessing) {
            return "Bệnh nhân này hiện đang có hồ sơ điều trị chưa hoàn thành";
        }

        MedicalRecord record = new MedicalRecord();
        record.setDoctor(doctor);
        record.setPatient(patient);
        record.setDiagnosis(request.getDiagnosis());

        medicalRecordRepository.save(record);

        return "Medical record created successfully";
    }
}
