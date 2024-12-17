package com.taskease.college.Repository;

import com.taskease.college.Model.Attendance;
import com.taskease.college.Model.Faculty.SpreedSheet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {
    @Query("SELECT s FROM Attendance s WHERE s.category = :category AND s.department.id = :departmentId")
    List<Attendance> findByCategoryAndDepartment(@Param("category") String category, @Param("departmentId") int departmentId);
}
