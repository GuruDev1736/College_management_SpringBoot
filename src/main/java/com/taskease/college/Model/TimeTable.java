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
@Table(name = "timetable")
public class TimeTable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;
    private String description;
    private String downloadUrl;
    private String category;

    @ManyToOne
    @JoinColumn(name = "yearId" , referencedColumnName = "id")
    private Year year;

    @ManyToOne
    @JoinColumn(name = "departmentId" , referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "batchId" , referencedColumnName = "id")
    private Batch batch;
}
