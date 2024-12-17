package com.taskease.college.Repository;

import com.taskease.college.Model.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepo extends JpaRepository<Attendance,Long> {

}
