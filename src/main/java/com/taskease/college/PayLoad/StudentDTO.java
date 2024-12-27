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
public class StudentDTO {

    private long id;
    private String fullName;
    private String email;
    private String password;
    private String phone;
    private String enrollment;
    private String profile_pic;
    private Date creationDate;
    private Boolean hostelAdmission = false;
}
