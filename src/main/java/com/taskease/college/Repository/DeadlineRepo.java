package com.taskease.college.Repository;


import com.taskease.college.Model.DeadLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeadlineRepo extends JpaRepository<DeadLine, Long> {
}
