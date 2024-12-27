package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Constants.Constants;
import com.taskease.college.Exceptions.ResourceNotFoundException;
import com.taskease.college.Model.Department;
import com.taskease.college.Model.Role;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.UserDTO;
import com.taskease.college.Repository.DepartmentRepo;
import com.taskease.college.Repository.RoleRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class UserServiceImpl implements UserService {

    private final ModelMapper modelMapper;

    private final UserRepo userRepo;

    private final PasswordEncoder passwordEncoder;

    private final DepartmentRepo departmentRepo;

    private final RoleRepo roleRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo, PasswordEncoder passwordEncoder, DepartmentRepo departmentRepo, RoleRepo roleRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.departmentRepo = departmentRepo;
        this.roleRepo = roleRepo;
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

}
