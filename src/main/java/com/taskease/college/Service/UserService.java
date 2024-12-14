package com.taskease.college.Service;


import com.taskease.college.PayLoad.UserDTO;

public interface UserService {

    UserDTO createFaculty(UserDTO userDTO , long societyId);
}
