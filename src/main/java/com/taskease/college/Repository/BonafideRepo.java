package com.taskease.college.Repository;

import com.taskease.college.Model.Bonafide;
import com.taskease.college.Model.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BonafideRepo extends JpaRepository<Bonafide,Long> {

    List<Bonafide> findByStudent(Student student);

    @Modifying
    @Transactional
    @Query("DELETE FROM Bonafide s WHERE s.id = :bonafideId")
    void deletebonafideById(@Param("bonafideId") long bonafideId);
}
