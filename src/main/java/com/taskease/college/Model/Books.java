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
@Table(name = "books")
public class Books {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String title;
    private String description;
    private String author;
    private String publisher;
    @Column(nullable = false , unique = true)
    private String bookLink;

    @ManyToOne
    @JoinColumn(name = "userId",referencedColumnName = "id")
    private User user;

}
