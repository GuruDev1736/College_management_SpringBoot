package com.taskease.college.PayLoad;


import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BooksDTO {

    private long id;
    private String title;
    private String description;
    private String author;
    private String publisher;
    private String bookLink;
    private UserDTO user;

}
