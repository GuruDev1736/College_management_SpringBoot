package com.taskease.college.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "academic_year")
public class Year {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String year;

    @OneToMany(mappedBy = "year", cascade = CascadeType.ALL, fetch = FetchType.LAZY , orphanRemoval = true)
    private Set<Student> students = new HashSet<>();
}
