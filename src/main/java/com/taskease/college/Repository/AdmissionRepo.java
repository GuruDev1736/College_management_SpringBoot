package com.taskease.college.Repository;

import com.taskease.college.Model.Admission;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdmissionRepo extends JpaRepository<Admission, Long> {
}
