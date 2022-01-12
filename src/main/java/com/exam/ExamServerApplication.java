package com.exam;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@SpringBootApplication
public class ExamServerApplication implements CommandLineRunner {

	@Autowired
	private UserService userService;

	public static void main(String[] args) {
		SpringApplication.run(ExamServerApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("starting code");	

		User user = new User();
		user.setUsername("ronny");
		user.setFirstName("ronny");
		user.setPassword("robox");
		user.setLastName("robox");
		user.setEnabled(true);
		user.setEmail("gagancool1211@gmail.com");
		user.setPhone("6387655221");
		user.setProfile("default.png");

		Role role = new Role();
		role.setRoleId(44L);
		role.setRoleName("Admin");

		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);
		
		Set<UserRole> set = new HashSet<>();
		set.add(userRole);

		User result = userService.createUser(user, set);
		System.out.println(result.getUsername()) ;
	}

}
