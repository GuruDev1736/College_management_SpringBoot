package com.taskease.college.Model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
@Setter
@Entity
@Table(name = "student")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String fullName;
    @Column(unique = true , nullable = false)
    private String email;
    @Column(nullable = false)
    private String password;
    @Column(unique = true , nullable = false)
    private String phone;
    @Column(unique = true, nullable = false)
    private String enrollment;
    private String profile_pic;
    private Date creationDate;
    private Boolean hostelAdmission;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE }, fetch = FetchType.EAGER)
    @JoinTable(name = "student_role", joinColumns = @JoinColumn(name = "student", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "role", referencedColumnName = "id"))
    private Set<Role> roles = new HashSet<>();

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "departmentId" , referencedColumnName = "id")
    private Department department;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "batchId" , referencedColumnName = "id")
    private Batch batch;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "year" , referencedColumnName = "id")
    private Year year;

    @OneToMany(mappedBy = "student",cascade = CascadeType.ALL , fetch = FetchType.LAZY , orphanRemoval = true)
    private Set<Admission> admissions = new HashSet<>();


}
