package com.taskease.college.Controller;


import com.taskease.college.Exceptions.ErrorException;
import com.taskease.college.Model.Student;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.*;
import com.taskease.college.Repository.StudentRepo;
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
    private final StudentRepo studentRepo;



    private final JwtHelper helper;

    public AuthController(UserDetailsService userDetailsService, AuthenticationManager manager, UserService userService, ModelMapper modelMapper, UserRepo userRepo, StudentRepo studentRepo, JwtHelper helper) {
        this.userDetailsService = userDetailsService;
        this.manager = manager;
        this.userService = userService;
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.studentRepo = studentRepo;
        this.helper = helper;
    }

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<JWTStudentResponse>> login(@RequestBody JWTRequest request) {
        // Authenticate the user
        this.doAuthenticate(request.getEmail(), request.getPassword());

        // Load user details from the user details service
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());

        // Generate JWT token
        String token = this.helper.generateToken(userDetails);

        // Check if the user exists as a User
        Optional<User> userOpt = this.userRepo.findByEmail(userDetails.getUsername());
        if (userOpt.isPresent()) {
            User user = userOpt.get();
            UserDTO userDTO = this.modelMapper.map(user, UserDTO.class);

            // Prepare response for User
            JWTStudentResponse response = createJwtResponseForUser(token, userDetails, userDTO);
            return new ResponseEntity<>(new ApiResponse<>("200", "User Logged In Successfully", response), HttpStatus.OK);
        }

        // Check if the user exists as a Student
        Optional<Student> studentOpt = this.studentRepo.findByEmail(userDetails.getUsername());
        if (studentOpt.isPresent()) {
            Student student = studentOpt.get();
            StudentDTO studentDTO = this.modelMapper.map(student, StudentDTO.class);

            // Prepare response for Student
            JWTStudentResponse response = createJwtResponseForStudent(token, userDetails, studentDTO);
            return new ResponseEntity<>(new ApiResponse<>("200", "Student Logged In Successfully", response), HttpStatus.OK);
        }

        // If neither User nor Student is found
        return new ResponseEntity<>(new ApiResponse<>("404", "User or Student not found", null), HttpStatus.NOT_FOUND);
    }

    private JWTStudentResponse createJwtResponseForStudent(String token, UserDetails userDetails, StudentDTO studentDTO) {
        return JWTStudentResponse.builder()
                .token(token)
                .userId(studentDTO.getId())
                .yearName(studentDTO.getYear() != null ? studentDTO.getYear().getYear() : "")
                .yearId(studentDTO.getYear() != null ? studentDTO.getYear().getId() : 0)
                .departmentName(studentDTO.getDepartment() != null ? studentDTO.getDepartment().getName() : "")
                .departmentId(studentDTO.getDepartment() != null ? studentDTO.getDepartment().getId() : 0)
                .batchId(studentDTO.getBatch() != null ? studentDTO.getBatch().getId() : 0)
                .batchName(studentDTO.getBatch() != null ? studentDTO.getBatch().getBatchName() : "")
                .hostelAllowance(studentDTO.getHostelAdmission() != null ? studentDTO.getHostelAdmission() : false)
                .fullName(studentDTO.getFullName() != null ? studentDTO.getFullName() : "")
                .userProfilePic(studentDTO.getProfile_pic() != null ? studentDTO.getProfile_pic() : "")
                .userRole(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse(""))
                .userName(userDetails.getUsername() != null ? userDetails.getUsername() : "")
                .build();
    }

    private JWTStudentResponse createJwtResponseForUser(String token, UserDetails userDetails, UserDTO userDTO) {
        return JWTStudentResponse.builder()
                .token(token)
                .userId(userDTO.getId())
                .yearId(0)
                .yearName("")
                .departmentName(userDTO.getDepartment() != null ? userDTO.getDepartment().getName() : "")
                .departmentId(userDTO.getDepartment() != null ? userDTO.getDepartment().getId() : 0)
                .batchName("")
                .batchId(0)
                .fullName(userDTO.getFullName() != null ? userDTO.getFullName() : "")
                .userProfilePic(userDTO.getProfile_pic() != null ? userDTO.getProfile_pic() : "")
                .userRole(userDetails.getAuthorities().stream()
                        .map(GrantedAuthority::getAuthority)
                        .findFirst()
                        .orElse(""))
                .userName(userDetails.getUsername() != null ? userDetails.getUsername() : "")
                .build();
    }


    @PostMapping("/faculty/register/{departmentId}")
    public ResponseEntity<ApiResponse<UserDTO>> createUser(@Valid @RequestBody UserDTO userDTO , @PathVariable int departmentId) {
        UserDTO createdUser = this.userService.createFaculty(userDTO,departmentId);
        return new ResponseEntity<>(new ApiResponse<>("200","Faculty Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/principle/register")
    public ResponseEntity<ApiResponse<UserDTO>> createPrinciple(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createPrincipal(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Principle Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/office/register")
    public ResponseEntity<ApiResponse<UserDTO>> createOffice(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createOffice(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Office Worker Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/super/register")
    public ResponseEntity<ApiResponse<UserDTO>> createSuperAdmin(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createSuperAdmin(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Super Admin Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/lib/register")
    public ResponseEntity<ApiResponse<UserDTO>> createLib(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createLibrarian(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Librarian Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/cooperative/register")
    public ResponseEntity<ApiResponse<UserDTO>> createCoOperative(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createCoOperative(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Co-Operative Created Successfully",createdUser), HttpStatus.OK);
    }

    @PostMapping("/hostel/register")
    public ResponseEntity<ApiResponse<UserDTO>> createWarden(@Valid @RequestBody UserDTO userDTO) {
        UserDTO createdUser = this.userService.createWarden(userDTO);
        return new ResponseEntity<>(new ApiResponse<>("200","Warden Created Successfully",createdUser), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<ApiResponse<String>> deleteUser(@PathVariable("id") long id) {
        userService.deleteUserById(id);
        return ResponseEntity.ok(new ApiResponse<>("200","user Deleted Successfully",""));
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
