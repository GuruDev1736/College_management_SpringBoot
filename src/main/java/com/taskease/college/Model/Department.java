package com.taskease.college.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(unique = true, nullable = false)
    private String name;
    private String description;

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Batch> batches = new HashSet<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<Student> students = new HashSet<>();


}
