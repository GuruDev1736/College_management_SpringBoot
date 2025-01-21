package com.taskease.college.Service;

import com.taskease.college.PayLoad.ReportDTO;

import java.util.List;

public interface ReportService {

    ReportDTO createReport(ReportDTO reportDTO , long studentId);
    ReportDTO updateReportStatus(ReportDTO reportDTO , long id);
    List<ReportDTO> getReports();
    ReportDTO getReport(long id);
    void deleteReport(long id);
    List<ReportDTO> getReportsByStudent(long studentId);
}
