package com.taskease.college.Repository;

import com.taskease.college.Model.Student;
import com.taskease.college.Model.User;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface StudentRepo extends JpaRepository<Student,Long> {

    Optional<Student> findByEmail(String email);

    @Modifying
    @Transactional
    @Query("DELETE FROM Student s WHERE s.id = :studentId")
    void deleteStudentById(@Param("studentId") long studentId);

}
