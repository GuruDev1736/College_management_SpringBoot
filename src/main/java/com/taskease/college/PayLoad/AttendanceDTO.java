package com.taskease.college.PayLoad;

import jakarta.persistence.Column;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AttendanceDTO {

    private long id;
    private Date createdDate;
    private String title;
    private String description;
    private String link;
    private String category;
}
