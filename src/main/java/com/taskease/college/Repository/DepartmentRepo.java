package com.taskease.college.Repository;

import com.taskease.college.Model.Department;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface DepartmentRepo extends JpaRepository<Department, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Department s WHERE s.id = :departmentId")
    void deleteDepartmentById(@Param("departmentId") int departmentId);
}
