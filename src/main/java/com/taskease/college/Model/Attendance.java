package com.taskease.college.Model;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attendance")
public class Attendance {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date createdDate;

    @Column(unique = true, nullable = false)
    private String title;
    private String description;
    @Column(unique = true, nullable = false)
    private String link;
    @Column(unique = true, nullable = false)
    private String category;
}
