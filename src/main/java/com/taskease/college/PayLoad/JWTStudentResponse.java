package com.taskease.college.PayLoad;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTStudentResponse {
    private  String token ;
    private String userName ;
    private Long userId ;
    private String yearName;
    private int yearId;
    private String departmentName ;
    private int departmentId;
    private String batchName;
    private int batchId;
    private boolean hostelAllowance;
    private String fullName;
    private String userRole;
    private String userProfilePic;
    private String enrollement;
}
