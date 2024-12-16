package com.taskease.college.Model;


import com.taskease.college.Model.Faculty.SpreedSheet;
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

    @OneToMany(mappedBy = "departments" , cascade =  CascadeType.ALL , fetch = FetchType.EAGER)
    private Set<User> users = new HashSet<>();

    @OneToMany(mappedBy = "department" , cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<SpreedSheet> spreedSheets = new HashSet<>();

}
