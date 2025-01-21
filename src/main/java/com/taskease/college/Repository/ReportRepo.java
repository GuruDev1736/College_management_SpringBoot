package com.taskease.college.Repository;


import com.google.firebase.database.core.Repo;
import com.taskease.college.Model.Report;
import com.taskease.college.Model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ReportRepo extends JpaRepository<Report, Long> {

    @Modifying
    @Transactional
    @Query("DELETE FROM Report s WHERE s.id = :reportId")
    void deleteReportById(@Param("reportId") long reportId);

    List<Report> findByStudent(Student student);
}
