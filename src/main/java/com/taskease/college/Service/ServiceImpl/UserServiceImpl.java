package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Constants.Constants;
import com.taskease.college.Model.Role;
import com.taskease.college.Model.User;
import com.taskease.college.PayLoad.UserDTO;
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

    private final RoleRepo roleRepo;

    public UserServiceImpl(ModelMapper modelMapper, UserRepo userRepo, PasswordEncoder passwordEncoder, RoleRepo roleRepo) {
        this.modelMapper = modelMapper;
        this.userRepo = userRepo;
        this.passwordEncoder = passwordEncoder;
        this.roleRepo = roleRepo;
    }

    @Override
    public UserDTO createFaculty(UserDTO userDTO, long societyId) {
        User user = this.modelMapper.map(userDTO, User.class);
        user.setPassword(passwordEncoder.encode(userDTO.getPassword()));

        Role role = this.roleRepo.findById(Constants.FACULTY_ROLE).get();
        user.getRoles().add(role);
        user.setJoinDate(new Date());

        User saved = this.userRepo.save(user);
        return this.modelMapper.map(saved,UserDTO.class);
    }

}
