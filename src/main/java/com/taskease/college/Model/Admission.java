package com.taskease.college.Model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "admission")
public class Admission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String studentName;
    private String department;
    private String year;
    private String address;
    private String studentPhoneNo;
    private String parentPhoneNo;
    private String schoolPercentage;
    private String diplomaPercentage;
    private String aadharLink;
    private Boolean enabled;

    @ManyToOne
    @JoinColumn(name = "studentId",referencedColumnName = "id")
    private Student student;

}
