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
public class ReportDTO {

    private long id;
    private String title;
    private String description;
    private String link;
    private String status = "LOST";
    private StudentDTO student;
}
