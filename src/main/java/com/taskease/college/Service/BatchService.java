package com.taskease.college.Service;

import com.taskease.college.Model.Batch;
import com.taskease.college.PayLoad.BatchDTO;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BatchService {

    BatchDTO createBatch(BatchDTO batchDTO , int departmentId);
}
