package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.ReportDTO;
import com.taskease.college.Service.ReportService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/report")
public class ReportController {

    private final ReportService reportService;

    public ReportController(ReportService reportService) {
        this.reportService = reportService;
    }

    @PostMapping("/create/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> createReport(@RequestBody ReportDTO reportDTO , @PathVariable("id") long studentId) {
        ReportDTO reportDTO1 = reportService.createReport(reportDTO , studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Report Creted Successfully", reportDTO1));
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> updateReport(@RequestBody ReportDTO reportDTO , @PathVariable("id") long reportId) {
        ReportDTO reportDTO1 = reportService.updateReportStatus(reportDTO, reportId);
        return ResponseEntity.ok(new ApiResponse<>("200","Report Updated", reportDTO1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<ReportDTO>>> getAllReports() {
        List<ReportDTO> reportDTOS = reportService.getReports();
        return ResponseEntity.ok(new ApiResponse<>("200","Report List Fetched Successfully", reportDTOS));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<ReportDTO>> getReportById(@PathVariable("id") long reportId) {
        ReportDTO reportDTO = reportService.getReport(reportId);
        return ResponseEntity.ok(new ApiResponse<>("200","Report Found", reportDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteReport(@PathVariable("id") long reportId) {
        reportService.deleteReport(reportId);
        return ResponseEntity.ok(new ApiResponse<>("200","Report Deleted Successfully",""));
    }

    @GetMapping("/student/{id}")
    public ResponseEntity<ApiResponse<List<ReportDTO>>> getStudentReports(@PathVariable("id") long studentId) {
        List<ReportDTO> reportDTOS = reportService.getReportsByStudent(studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Report List Fetched Successfully", reportDTOS));
    }
}
