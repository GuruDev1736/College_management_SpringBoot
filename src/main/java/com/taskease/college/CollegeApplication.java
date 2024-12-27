package com.taskease.college;

import com.taskease.college.Constants.Constants;
import com.taskease.college.Model.Role;
import com.taskease.college.Model.Year;
import com.taskease.college.Repository.RoleRepo;
import com.taskease.college.Repository.UserRepo;
import com.taskease.college.Repository.YearRepo;
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
	private final YearRepo yearRepo ;

	private final UserRepo userRepo ;

	private final PasswordEncoder passwordEncoder ;

	public CollegeApplication(RoleRepo roleRepo, YearRepo yearRepo, UserRepo userRepo, PasswordEncoder passwordEncoder) {
		this.roleRepo = roleRepo;
        this.yearRepo = yearRepo;
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


			Year year = new Year();
			year.setId(Constants.FIRST_YEAR);
			year.setYear(Constants.FIRST_YEAR_NAME);

			Year second = new Year();
			second.setId(Constants.SECOND_YEAR);
			second.setYear(Constants.SECOND_YEAR_NAME);

			Year third = new Year();
			third.setId(Constants.THIRD_YEAR);
			third.setYear(Constants.THIRD_YEAR_NAME);

			Year fourth = new Year();
			fourth.setId(Constants.FOURTH_YEAR);
			fourth.setYear(Constants.FOURTH_YEAR_NAME);

			List<Year> years = List.of(year, second, third, fourth);
			yearRepo.saveAll(years);

		}catch (Exception e ){
			e.printStackTrace();
		}
	}
}
