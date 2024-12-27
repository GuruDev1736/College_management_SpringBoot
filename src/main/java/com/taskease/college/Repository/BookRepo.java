package com.taskease.college.Repository;

import com.taskease.college.Model.Books;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface BookRepo extends JpaRepository<Books, Long> {

}
