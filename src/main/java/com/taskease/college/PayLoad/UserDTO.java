package com.taskease.college.PayLoad;


import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {

    private Long id ;
    private String fullName ;

    @Email(message = "Email Address is not valid")
    private String email;
    @Size(min = 8, max = 15, message = "Password must be min 8 characters")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @Size(min = 10, max = 10, message = "Phone No must be 10 Digits")
    private String phoneNo;
    private String profile_pic ;
    private String designation;
    private String address;
    private Boolean enabled = false ;
    private boolean completeProfile = false;
    private Date joinDate;


}
