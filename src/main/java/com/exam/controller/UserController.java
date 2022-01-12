package com.exam.controller;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.exam.model.Role;
import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.service.UserService;

@RestController
@RequestMapping("/user")
@CrossOrigin
public class UserController {

	@Autowired
	private UserService userService;

	// create user
	@PostMapping("/")
	public User createUser(@RequestBody User user) {

		Role role = new Role();
		role.setRoleId(45L);
		role.setRoleName("Normal");

		Set<UserRole> userRoles = new HashSet<>();
		UserRole userRole = new UserRole();
		userRole.setRole(role);
		userRole.setUser(user);

		userRoles.add(userRole);

		return userService.createUser(user, userRoles);
	}
	
	//getting user
	@GetMapping("/{username}")
	public User getUser(@PathVariable("username")String username) {
		return this.userService.getUser(username);
	}
	
	//deleting the user
	@DeleteMapping("/{id}")
	public void deleteUser(@PathVariable("id")Long id) {
		userService.deleteUser(id);
	}
	
	//update mapping
	@PutMapping("/")
	public User updateUser(@RequestBody User user) {
		return userService.udpateUser(user);
	}
}
