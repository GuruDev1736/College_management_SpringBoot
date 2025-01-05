package com.taskease.college.Repository;

import com.taskease.college.Model.Year;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface YearRepo extends JpaRepository<Year, Integer> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Year s WHERE s.id = :yearId")
    void deleteYearById(@Param("yearId") int yearId);
}
