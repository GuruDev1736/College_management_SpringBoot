package com.taskease.college.Service;


import com.taskease.college.PayLoad.UserDTO;

public interface UserService {

    UserDTO createFaculty(UserDTO userDTO , int departmentId);
    UserDTO createPrincipal(UserDTO userDTO);
    UserDTO createOffice(UserDTO userDTO);
    UserDTO createWarden(UserDTO userDTO);
    UserDTO createLibrarian(UserDTO userDTO);
    UserDTO createCoOperative (UserDTO userDTO);
    UserDTO createSuperAdmin(UserDTO userDTO);
    UserDTO getUserById(long id);
    void deleteUserById(long id);
}
