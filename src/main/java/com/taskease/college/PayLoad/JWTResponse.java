package com.taskease.college.PayLoad;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTResponse {
    private  String token ;
    private String userName ;
    private Long userId ;
    private String fullName;
    private String userRole;
    private String userProfilePic;
}
