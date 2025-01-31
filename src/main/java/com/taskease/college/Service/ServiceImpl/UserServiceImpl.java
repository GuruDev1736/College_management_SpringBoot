package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Constants.Constants;
import com.taskease.college.Constants.EmailService;
import com.taskease.college.Constants.OTPService;
import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Department;
import com.taskease.college.Model.Role;
import com.taskease.college.Model.Student;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.RoleDTO;
import com.taskease.college.PayLoad.UserDTO;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Repository.RoleRepo;
import com.taskease.college.Repository.StudentRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final DepartmentRepo departmentRepo;

    private final RoleRepo roleRepo;

    private final OTPService otpService;

    private final EmailService emailService;

    private final StudentRepo studentRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo, PasswordEncoder passwordEncoder, DepartmentRepo departmentRepo, RoleRepo roleRepo, OTPService otpService, EmailService emailService, StudentRepo studentRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepo = departmentRepo;
        this.roleRepo = roleRepo;
        this.otpService = otpService;
        this.emailService = emailService;
        this.studentRepo = studentRepo;
    }

    @Override
    public UserDTO createFaculty(UserDTO userDTO, int departmentId) {

        Department department = this.departmentRepo.findById(departmentId)
                .orElseThrow(()-> new ResourceNotFoundException("Department","Id",departmentId));

        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(department);

        Role role = this.roleRepo.findById(Constants.FACULTY_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createPrincipal(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.PRINCIPLE_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createOffice(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.OFFICE_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createWarden(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.WARDEN_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createLibrarian(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.LIB_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createCoOperative(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.COOPERATIVE_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO createSuperAdmin(UserDTO userDTO) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));
        user.setDepartment(null);

        Role role = this.roleRepo.findById(Constants.SUPER_ADMIN_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());
        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

    @Override
    public UserDTO getUserById(long id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        return this.modelMapper.map(user,UserDTO.class);
    }

    @Override
    public void deleteUserById(long id) {
        User user = this.userRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("User","Id",id));
        user.getRoles().clear(); // Clear associated roles
        user.getSpreedSheets().clear(); // Clear associated spreadsheets
        user.getDeadLines().clear(); // Clear associated deadlines
        user.getBooks().clear();
        userRepo.delete(user);
    }

    @Override
    public List<UserDTO> getUsersByRole(String role) {
        List<UserDTO> userDTOS = userRepo.findByRoleName(role).stream().map(user -> this.modelMapper.map(user,UserDTO.class)).toList();
        return userDTOS;
    }

    @Override
    public List<RoleDTO> getAllRoles() {
        List<RoleDTO> roleDTOS = roleRepo.findAll().stream().map(role -> this.modelMapper.map(role,RoleDTO.class)).toList();
        return roleDTOS;
    }

    @Override
    public void sendOTP(String email) {

        boolean isUserFound = false;

        Optional<User> optionalUser = this.userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            isUserFound = true;
        }

        if (!isUserFound) {
            Optional<Student> optionalRentant = this.studentRepo.findByEmail(email);
            if (!optionalRentant.isPresent()) {
                throw new ResourceNotFoundException("Faculty or Student", "email : " + email, 0);
            }
        }
        String otp = otpService.generateOtp(email);
        emailService.sendOtp(email, otp);
    }

    @Override
    public Boolean validateOTP(String email, String otp) {
        boolean isValid = otpService.validateOtp(email, otp);
        if (isValid) {
            otpService.clearOtp(email);
            return true;
        } else {
            return false;
        }
    }

    @Override
    public void changePassword(String email , String password) {
        boolean isUserUpdated = false;

        Optional<User> optionalUser = this.userRepo.findByEmail(email);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setPassword(passwordEncoder.encode(password));
            this.userRepo.save(user);
            isUserUpdated = true;
        }

        if (!isUserUpdated) {
            Student rentant = this.studentRepo.findByEmail(email)
                    .orElseThrow(() -> new ResourceNotFoundException("Student", "email", 0));
            rentant.setPassword(passwordEncoder.encode(password));
            this.studentRepo.save(rentant);
        }
    }
}
