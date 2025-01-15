package com.taskease.college.Controller;


import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.DepartmentDTO;
import com.taskease.college.Service.DepartmentService;
import org.apache.coyote.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/department")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping("/create")
    public ResponseEntity<ApiResponse<DepartmentDTO>> createDepartment(@RequestBody DepartmentDTO departmentDTO){
        DepartmentDTO createdDepartment = this.departmentService.creteDepartment(departmentDTO);
        return ResponseEntity.ok(new ApiResponse<>("200","Department Created Successfully",createdDepartment));
    }

    @GetMapping("/")
    public ResponseEntity<ApiResponse<List<DepartmentDTO>>> getAllDepartments(){
        List<DepartmentDTO> list = this.departmentService.getAllDepartmentList();
        return ResponseEntity.ok(new ApiResponse<>("200","Department Fetched Successfully",list));
    }

    @DeleteMapping("/{departmentId}")
    public ResponseEntity<ApiResponse<String>> deleteDepartment(@PathVariable("departmentId") int departmentId){
        this.departmentService.deleteDepartment(departmentId);
        return ResponseEntity.ok(new ApiResponse<>("200","Department Deleted Successfully",""));
    }
}
