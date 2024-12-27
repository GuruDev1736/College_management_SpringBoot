package com.taskease.college.Repository;

import com.taskease.college.Model.Year;
import org.springframework.data.jpa.repository.JpaRepository;

public interface YearRepo extends JpaRepository<Year, Integer> {
}
