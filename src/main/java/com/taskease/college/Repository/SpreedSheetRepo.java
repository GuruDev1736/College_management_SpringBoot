package com.taskease.college.Repository;

import com.taskease.college.Model.Faculty.SpreedSheet;
import com.taskease.college.PayLoad.SpreedSheetDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface SpreedSheetRepo extends JpaRepository<SpreedSheet,Long> {

    @Query("SELECT s FROM SpreedSheet s WHERE s.category = :category AND s.department.id = :departmentId")
    List<SpreedSheet> findByCategoryAndDepartment(@Param("category") String category, @Param("departmentId") int departmentId);

    @Query("SELECT s FROM SpreedSheet s WHERE s.category = :category AND s.department.id = :departmentId AND s.user.id = :userId")
    List<SpreedSheet> findByCategoryAndDepartmentAndUser(@Param("category") String category, @Param("departmentId") int departmentId, @Param("userId") long userId);

    @Query("SELECT s FROM SpreedSheet s WHERE s.category = :category")
    List<SpreedSheet> findByCategory(@Param("category") String category);

}
