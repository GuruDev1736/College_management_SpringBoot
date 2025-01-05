package com.taskease.college.Service;

import com.taskease.college.PayLoad.AdmissionDTO;

import java.util.List;

public interface AdmissionService {

    AdmissionDTO addAdmission(AdmissionDTO admissionDTO , long studentId);
    void approveAdmission(long admissionId);
    void rejectAdmission(long admissionId);
    List<AdmissionDTO> getAllAdmission();

}
