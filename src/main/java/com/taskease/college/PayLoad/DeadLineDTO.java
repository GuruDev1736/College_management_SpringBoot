package com.taskease.college.PayLoad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class DeadLineDTO {

    private long id;

    private String title;
    private String description;
    private String startDate ;
    private String endDate ;
    private Date createdDate = new Date();
    private UserDTO user;
}
