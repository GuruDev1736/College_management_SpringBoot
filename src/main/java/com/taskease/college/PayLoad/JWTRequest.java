package com.taskease.college.PayLoad;


import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class JWTRequest {

    private  String email ;
    private  String password ;
}
