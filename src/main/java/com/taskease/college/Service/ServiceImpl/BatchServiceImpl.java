package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Batch;
import com.taskease.college.Model.Department;
import com.taskease.college.PayLoad.BatchDTO;
import com.taskease.college.Repository.BatchRepo;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Service.BatchService;
import org.modelmapper.ModelMapper;
import org.springframework.boot.autoconfigure.web.servlet.error.BasicErrorController;
import org.springframework.stereotype.Service;

@Service
public class BatchServiceImpl implements BatchService {
    private final BatchRepo batchRepo;
    private final ModelMapper modelMapper;
    private final DepartmentRepo departmentRepo;
    private final BasicErrorController basicErrorController;

    public BatchServiceImpl(BatchRepo batchRepo, ModelMapper modelMapper, DepartmentRepo departmentRepo, BasicErrorController basicErrorController) {
        this.batchRepo = batchRepo;
        this.modelMapper = modelMapper;
        this.departmentRepo = departmentRepo;
        this.basicErrorController = basicErrorController;
    }

    @Override
    public BatchDTO createBatch(BatchDTO batchDTO , int departmentId) {

        Department department = this.departmentRepo.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));
        Batch batch = this.modelMapper.map(batchDTO, Batch.class);
        batch.setDepartment(department);
        Batch save = this.batchRepo.save(batch);
        return this.modelMapper.map(save,BatchDTO.class);
    }

}
