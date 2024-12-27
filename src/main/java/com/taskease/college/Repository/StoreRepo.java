package com.taskease.college.Repository;

import com.taskease.college.Model.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepo extends JpaRepository<Store, Integer> {

    Optional<Store> findByCategory(String category);

}
