package com.taskease.college.Service;

import com.taskease.college.PayLoad.StudentDTO;

import java.util.List;

public interface StudentService {

    StudentDTO createStudent(StudentDTO studentDTO , int yearId , int department , int batchId);
    List<StudentDTO> getAllStudent();
    StudentDTO getStudentById(long id);
    void deleteStudent(long studentId);
}
