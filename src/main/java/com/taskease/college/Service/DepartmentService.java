package com.taskease.college.Service;

import com.taskease.college.Model.Department;
import com.taskease.college.PayLoad.DepartmentDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DepartmentService{

    DepartmentDTO creteDepartment(DepartmentDTO departmentDTO);
    List<DepartmentDTO> getAllDepartmentList();
    void deleteDepartment(int departmentId);
}
