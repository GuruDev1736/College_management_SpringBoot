package com.taskease.college.Controller;


import com.taskease.college.PayLoad.AdmissionDTO;
import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.Service.AdmissionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admission")
public class AdmissionController {

    private final AdmissionService admissionService;

    public AdmissionController(AdmissionService admissionService) {
        this.admissionService = admissionService;
    }

    @PostMapping("/{studentId}")
    public ResponseEntity<ApiResponse<AdmissionDTO>> addAdmission(@RequestBody AdmissionDTO admissionDTO , @PathVariable long studentId) {
        AdmissionDTO dto = admissionService.addAdmission(admissionDTO, studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Admission Added Successfully",dto));

    }

    @PutMapping("/approve/{admissionId}")
    public ResponseEntity<ApiResponse<String>> approveAdmission(@PathVariable long admissionId) {
        admissionService.approveAdmission(admissionId);
        return ResponseEntity.ok(new ApiResponse<>("200","Admission Approved Successfully",""));
    }

    @PutMapping("/reject/{admissionId}")
    public ResponseEntity<ApiResponse<String>> rejectAdmission(@PathVariable long admissionId) {
        admissionService.rejectAdmission(admissionId);
        return ResponseEntity.ok(new ApiResponse<>("200","Admission Rejected Successfully",""));
    }
}
