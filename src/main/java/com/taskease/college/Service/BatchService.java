package com.taskease.college.Service;

import com.taskease.college.Model.Batch;
import com.taskease.college.PayLoad.BatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BatchService {

    BatchDTO createBatch(BatchDTO batchDTO , int departmentId);
    void deleteBatch(int batchId);
    List<BatchDTO> getBatchList();
    List<BatchDTO> getBatchByDepartmentList(int departmentId);
}
