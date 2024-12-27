package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Admission;
import com.taskease.college.Model.Student;
import com.taskease.college.PayLoad.AdmissionDTO;
import com.taskease.college.Repository.AdmissionRepo;
import com.taskease.college.Repository.StudentRepo;
import com.taskease.college.Service.AdmissionService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class AdmissionServiceImpl implements AdmissionService {

    private final ModelMapper modelMapper;
    private final AdmissionRepo admissionRepo;
    private final StudentRepo studentRepo;

    public AdmissionServiceImpl(ModelMapper modelMapper, AdmissionRepo admissionRepo, StudentRepo studentRepo) {
        this.modelMapper = modelMapper;
        this.admissionRepo = admissionRepo;
        this.studentRepo = studentRepo;
    }

    @Override
    public AdmissionDTO addAdmission(AdmissionDTO admissionDTO , long studentId) {
        Student student = this.studentRepo.findById(studentId).orElseThrow(()-> new ResourceNotFoundException("Student","id",studentId));
        Admission admission = this.modelMapper.map(admissionDTO, Admission.class);
        admission.setStudent(student);
        this.admissionRepo.save(admission);
        return this.modelMapper.map(admission, AdmissionDTO.class);
    }

    @Override
    public void approveAdmission(long admissionId) {
        Admission admission = this.admissionRepo.findById(admissionId).orElseThrow(()-> new ResourceNotFoundException("Admission","id",admissionId));
        admission.setEnabled(true);
        this.admissionRepo.save(admission);
        Student student = this.studentRepo.findById(admission.getStudent().getId()).orElseThrow(()-> new ResourceNotFoundException("Student","id",admission.getStudent().getId()));
        student.setHostelAdmission(true);
        this.studentRepo.save(student);
    }

    @Override
    public void rejectAdmission(long admissionId) {
        Admission admission = this.admissionRepo.findById(admissionId).orElseThrow(()-> new ResourceNotFoundException("Admission","id",admissionId));
        admission.setEnabled(false);
        this.admissionRepo.save(admission);
        Student student = this.studentRepo.findById(admission.getStudent().getId()).orElseThrow(()-> new ResourceNotFoundException("Student","id",admission.getStudent().getId()));
        student.setHostelAdmission(false);
        this.studentRepo.save(student);
    }
}
