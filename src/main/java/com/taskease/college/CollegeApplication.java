package com.taskease.college;

import com.taskease.college.Constants.Constants;
import com.taskease.college.Model.Role;
import com.taskease.college.Repository.RoleRepo;
import com.taskease.college.Repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootApplication
public class CollegeApplication implements CommandLineRunner {

	private final RoleRepo roleRepo ;

	private final UserRepo userRepo ;

	private final PasswordEncoder passwordEncoder ;

	public CollegeApplication(RoleRepo roleRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.roleRepo = roleRepo;
		this.userRepo = userRepo;
		this.passwordEncoder = passwordEncoder;
	}

	public static void main(String[] args) {
		SpringApplication.run(CollegeApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Override
	public void run(String... args) throws Exception {
		try{
			Role role = new Role();
			role.setId(Constants.SUPER_ADMIN_ROLE);
			role.setRoleName(Constants.SUPER_ADMIN_ROLE_NAME);

			Role faculty = new Role();
			faculty.setId(Constants.FACULTY_ROLE);
			faculty.setRoleName(Constants.FACULTY_ROLE_NAME);

			Role principal = new Role();
			principal.setId(Constants.PRINCIPLE_ROLE);
			principal.setRoleName(Constants.PRINCIPLE_ROLE_NAME);

			Role librarian = new Role();
			librarian.setId(Constants.LIB_ROLE);
			librarian.setRoleName(Constants.LIB_ROLE_NAME);

			Role cop = new Role();
			cop.setId(Constants.COOPERATIVE_ROLE);
			cop.setRoleName(Constants.COOPERATIVE_ROLE_NAME);

			Role warden = new Role();
			warden.setId(Constants.WARDEN_ROLE);
			warden.setRoleName(Constants.WARDEN_ROLE_NAME);

			Role office = new Role();
			office.setId(Constants.OFFICE_ROLE);
			office.setRoleName(Constants.OFFICE_ROLE_NAME);

			Role student = new Role();
			student.setId(Constants.STUDENT_ROLE);
			student.setRoleName(Constants.STUDENT_ROLE_NAME);

			List<Role> roles = List.of(role, faculty, principal, librarian, cop, warden, office,student);
			roleRepo.saveAll(roles);

		}catch (Exception e ){
			e.printStackTrace();
		}
	}
}
