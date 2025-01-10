package com.taskease.college.Repository;

import com.taskease.college.Model.Faculty.SpreedSheet;
import com.taskease.college.Model.TimeTable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TimeTableRepo extends JpaRepository<TimeTable, Integer> {

    @Query("SELECT s FROM TimeTable s WHERE s.category = :category")
    List<TimeTable> findByCategory(@Param("category") String category);

    @Query("SELECT t FROM TimeTable t WHERE t.year.id = :yearId AND t.department.id = :departmentId AND t.batch.id = :batchId AND t.category = :category")
    List<TimeTable> findByDetails(@Param("yearId") int yearId,
                                  @Param("departmentId") int departmentId,
                                  @Param("batchId") int batchId,
                                  @Param("category") String category);

}
