package com.taskease.college.PayLoad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TimeTableDTO {

    private int id;

    private String title;
    private String description;
    private String downloadUrl;
    private String category;
}
