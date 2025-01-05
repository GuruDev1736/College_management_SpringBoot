package com.taskease.college.Repository;

import com.taskease.college.Model.Batch;
import com.taskease.college.Model.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BatchRepo extends JpaRepository<Batch, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Batch s WHERE s.id = :batchId")
    void deleteBatchById(@Param("batchId") int batchId);

    List<Batch> findByDepartment(Department department);
}
