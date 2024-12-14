package com.taskease.college.Controller;


import com.taskease.college.Exceptions.ErrorException;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.ApiResponse;
import com.taskease.college.PayLoad.JWTRequest;
import com.taskease.college.PayLoad.JWTResponse;
import com.taskease.college.PayLoad.UserDTO;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Security.JwtHelper;
import com.taskease.college.Service.UserService;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final UserDetailsService userDetailsService;

    private final AuthenticationManager manager;

    private final UserService userService;

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;



    private final JwtHelper helper;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, UserService userService, ModelMapper modelMapper, UserRepo userRepo, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.helper = helper;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JWTResponse>> login(@RequestBody JWTRequest request) {

        this.doAuthenticate(request.getEmail(), request.getPassword());

        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);

        Optional<User> userOpt = this.userRepo.findByEmail(userDetails.getUsername());

        JWTResponse response;
            // User login
            User user = userOpt.get();
            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);

            response = JWTResponse.builder()
                    .token(token)
                    .userId(userDTO.getId())
                    .fullName(userDTO.getFullName())
                    .userProfilePic(userDTO.getProfile_pic())
                    .userRole(userDetails.getAuthorities().stream()
                            .map(GrantedAuthority::getAuthority)
                            .findFirst()
                            .orElse(""))
                    .userName(userDetails.getUsername()).build();

            return new ResponseEntity<>(new ApiResponse<>("200", "User Logged Successfully", response), HttpStatus.OK);
    }

    @PostMapping("/user/register")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO , @PathVariable long societyId) {
        UserDTO createdUser = this.userService.createUser(userDTO,societyId);
        return new ResponseEntity<>(new ApiResponse<>("200","User Created Successfully",createdUser), HttpStatus.OK);
    }

    private void doAuthenticate(String email, String password) {

        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            manager.authenticate(authentication);

        } catch (BadCredentialsException e) {
            throw new ErrorException(" Invalid Username or Password  !!");
        }

    }

    @ExceptionHandler(BadCredentialsException.class)
    public String exceptionHandler() {
        return "Credentials Invalid !!";
    }

}
