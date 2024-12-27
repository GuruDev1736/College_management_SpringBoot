package com.taskease.college.Repository;

import com.taskease.college.Model.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockRepo extends JpaRepository<Stock, Integer> {
}
