package com.taskease.college.Service.ServiceImpl;


import com.taskease.college.Model.Role;
import com.taskease.college.Model.User;
import com.taskease.college.Repository.UserRepo;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepo userRepo;

    public CustomUserDetailService(UserRepo userRepo) {
        this.userRepo = userRepo;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Check if the username belongs to a user or company
        UserDetails userDetails = loadUserDetails(username);
        if (userDetails == null) {
            throw new UsernameNotFoundException("User or Company not found with username: " + username);
        }
        return userDetails;
    }

    private UserDetails loadUserDetails(String username) {
        // Try to find as User
        User user = userRepo.findByEmail(username).orElse(null);
        if (user != null) {
            return createUserDetails(user.getEmail(), user.getPassword(), user.getRoles());
        }

        return null; // Return null if neither User nor Company found
    }

    private UserDetails createUserDetails(String email, String password, Set<Role> roles) {
        Set<GrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }

    private UserDetails createCompanyDetails(String email, String password, Set<Role> roles) {
        // Create and return CompanyDetails if you have a specific implementation
        Set<GrantedAuthority> authorities = roles
                .stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .collect(Collectors.toSet());
        return new org.springframework.security.core.userdetails.User(email, password, authorities);
    }
}
