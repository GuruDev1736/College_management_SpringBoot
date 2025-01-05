package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.StudentDTO;
import com.taskease.college.Service.StudentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @PostMapping("/create/{yearId}/{departmentId}/{batchId}")
    public ResponseEntity<ApiResponse<StudentDTO>> createStudent(@RequestBody StudentDTO studentDTO , @PathVariable("yearId") int yearId , @PathVariable("departmentId") int departmentId , @PathVariable("batchId") int batchId ) {
        StudentDTO studentDTO1 = studentService.createStudent(studentDTO , yearId , departmentId , batchId);
        return ResponseEntity.ok(new ApiResponse<>("200","Student Created",studentDTO1));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> studentDTOList = studentService.getAllStudent();
        return ResponseEntity.ok(new ApiResponse<>("200","Student List Fetched Successfully",studentDTOList));
    }

    @GetMapping("/{studentId}")
    public ResponseEntity<ApiResponse<StudentDTO>> getStudent(@PathVariable("studentId") int studentId) {
        StudentDTO studentDTO = studentService.getStudentById(studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Student Fetched Successfully",studentDTO));
    }

    @DeleteMapping("/{studentId}")
    public ResponseEntity<ApiResponse<String>> deleteStudent(@PathVariable("studentId") long studentId) {
        studentService.deleteStudent(studentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Student Deleted Successfully",""));
    }
}
