package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Report;
import com.taskease.college.Model.Student;
import com.taskease.college.PayLoad.ReportDTO;
import com.taskease.college.Repository.ReportRepo;
import com.taskease.college.Repository.StudentRepo;
import com.taskease.college.Service.ReportService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReportServiceImpl implements ReportService {

    private final ReportRepo reportRepo;
    private final ModelMapper modelMapper;
    private final StudentRepo studentRepo;

    public ReportServiceImpl(ReportRepo reportRepo, ModelMapper modelMapper, StudentRepo studentRepo) {
        this.reportRepo = reportRepo;
        this.modelMapper = modelMapper;
        this.studentRepo = studentRepo;
    }


    @Override
    public ReportDTO createReport(ReportDTO reportDTO , long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        Report report = modelMapper.map(reportDTO, Report.class);
        report.setStudent(student);
        Report savedReport = reportRepo.save(report);
        return modelMapper.map(savedReport, ReportDTO.class);
    }

    @Override
    public ReportDTO updateReportStatus(ReportDTO reportDTO , long reportId) {
        Report report = reportRepo.findById(reportId).orElseThrow(()-> new ResourceNotFoundException("Report","Id",reportId));
        report.setTitle(reportDTO.getTitle());
        report.setDescription(reportDTO.getDescription());
        report.setStatus(reportDTO.getStatus());
        report.setLink(reportDTO.getLink());
        Report savedReport = reportRepo.save(report);
        return modelMapper.map(savedReport, ReportDTO.class);
    }

    @Override
    public List<ReportDTO> getReports() {
        List<ReportDTO> reportDTOS = reportRepo.findAll().stream().map(x -> modelMapper.map(x, ReportDTO.class)).toList();
        return reportDTOS;
    }

    @Override
    public ReportDTO getReport(long id) {
        Report report = reportRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Report","Id",id));
        return modelMapper.map(report, ReportDTO.class);
    }

    @Override
    public void deleteReport(long reportId) {
        reportRepo.deleteReportById(reportId);
    }

    @Override
    public List<ReportDTO> getReportsByStudent(long studentId) {
        Student student = studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","Id",studentId));
        List<ReportDTO> reportDTOS = reportRepo.findByStudent(student).stream().map(x -> modelMapper.map(x, ReportDTO.class)).toList();
        return reportDTOS;
    }
}
