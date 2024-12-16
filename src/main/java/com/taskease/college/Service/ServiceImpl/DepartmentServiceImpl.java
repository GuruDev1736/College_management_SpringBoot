package com.taskease.college.Service.ServiceImpl;

import com.taskease.college.Model.Department;
import com.taskease.college.PayLoad.DepartmentDTO;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Service.DepartmentService;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final ModelMapper modelMapper;
    private final DepartmentRepo departmentRepo;

    public DepartmentServiceImpl(ModelMapper modelMapper, DepartmentRepo departmentRepo) {
        this.modelMapper = modelMapper;
        this.departmentRepo = departmentRepo;
    }

    @Override
    public DepartmentDTO creteDepartment(DepartmentDTO departmentDTO) {
        Department department = this.modelMapper.map(departmentDTO, Department.class);
        Department save = this.departmentRepo.save(department);
        return this.modelMapper.map(save,DepartmentDTO.class);
    }
}
