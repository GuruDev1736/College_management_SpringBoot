package com.taskease.college.PayLoad;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AdmissionDTO {

    private long id;

    private String studentName;
    private String department;
    private String year;
    private String address;
    private String studentPhoneNo;
    private String parentPhoneNo;
    private String schoolPercentage;
    private String diplomaPercentage;
    private String aadharLink;
    private Boolean enabled = false;
}
