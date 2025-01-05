package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Constants.Constants;
import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.*;
import com.taskease.college.PayLoad.BatchDTO;
import com.taskease.college.PayLoad.StudentDTO;
import com.taskease.college.Repository.*;
import com.taskease.college.Service.StudentService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class StudentServiceImpl implements StudentService {

    private final ModelMapper modelMapper;
    private final StudentRepo studentRepo;
    private final DepartmentRepo departmentRepo;
    private final YearRepo yearRepo;
    private final BatchRepo batchRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    public StudentServiceImpl(ModelMapper modelMapper, StudentRepo studentRepo, DepartmentRepo departmentRepo, YearRepo yearRepo, BatchRepo batchRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.modelMapper = modelMapper;
        this.studentRepo = studentRepo;
        this.departmentRepo = departmentRepo;
        this.yearRepo = yearRepo;
        this.batchRepo = batchRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public StudentDTO createStudent(StudentDTO studentDTO, int yearId, int department, int batchId) {

        Year year = yearRepo.findById(yearId).orElseThrow(()-> new ResourceNotFoundException("Year","id",yearId));
        Department department1 = departmentRepo.findById(department).orElseThrow(()-> new ResourceNotFoundException("Department","id",department));
        Batch batch = batchRepo.findById(batchId).orElseThrow(()-> new ResourceNotFoundException("Batch","id",batchId));
        Student student = modelMapper.map(studentDTO, Student.class);
        student.setPassword(passwordEncoder.encode(student.getPassword()));
        student.setYear(year);
        student.setDepartment(department1);
        student.setBatch(batch);

        Role role = this.roleRepo.findById(Constants.STUDENT_ROLE).get();
        student.getRoles().add(role);

        return modelMapper.map(studentRepo.save(student), StudentDTO.class);
    }

    @Override
    public List<StudentDTO> getAllStudent() {
        List<StudentDTO> studentDTOS = studentRepo.findAll().stream().map(student -> modelMapper.map(student, StudentDTO.class)).toList();
        return studentDTOS;
    }

    @Override
    public StudentDTO getStudentById(long id) {
        Student student = studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student","id",id));
        return modelMapper.map(student, StudentDTO.class);
    }

    @Override
    public void deleteStudent(long studentId) {
        Student student = studentRepo.findById(studentId)
                .orElseThrow(() -> new ResourceNotFoundException("Student", "id", studentId));
        studentRepo.deleteStudentById(studentId);
    }


}
