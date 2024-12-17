package com.taskease.college.Model.Faculty;

import com.taskease.college.Model.Department;
import com.taskease.college.Model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "spreedsheet")
public class SpreedSheet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date creationDate;
    @Column(unique = true, nullable = false)
    private String title ;
    private String description ;
    @Column(unique = true, nullable = false)
    private String link ;
    private String imageLink;
    @Column(nullable = false)
    private String category;

    @ManyToOne
    @JoinColumn(name = "departmentId" , referencedColumnName = "id")
    private Department department;

    @ManyToOne
    @JoinColumn(name = "userId" , referencedColumnName = "id")
    private User user;

}
