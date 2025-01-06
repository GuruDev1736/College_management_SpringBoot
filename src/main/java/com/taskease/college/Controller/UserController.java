package com.taskease.college.Controller;

import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.RoleDTO;
import com.taskease.college.PayLoad.UserDTO;
import com.taskease.college.Service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserDTO>> getuserById(@PathVariable int id) {
        UserDTO userDTO = this.userService.getUserById(id);
        return ResponseEntity.ok(new ApiResponse<>("200","User Fetched Successfully",userDTO));
    }

    @GetMapping("/role/{roleName}")
    public ResponseEntity<ApiResponse<List<UserDTO>>> getuserByRole(@PathVariable String roleName) {
        List<UserDTO> userDTOS = userService.getUsersByRole(roleName);
        return ResponseEntity.ok(new ApiResponse<>("200","User Fetched Successfully",userDTOS));
    }

    @GetMapping("/role")
    public ResponseEntity<ApiResponse<List<RoleDTO>>> getuserByRole() {
        List<RoleDTO> allRoles = userService.getAllRoles();
        return ResponseEntity.ok(new ApiResponse<>("200","Roles Fetched Successfully",allRoles));
    }
}
