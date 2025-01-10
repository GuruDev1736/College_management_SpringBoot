package com.taskease.college.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "batch")
public class Batch {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String batchName;
    private String batchDescription;

    @ManyToOne
    @JoinColumn(name = "departmentId", referencedColumnName = "id")
    private Department department;

    @OneToMany(mappedBy = "batch" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "batch" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<TimeTable> timeTables = new HashSet<>();
}
