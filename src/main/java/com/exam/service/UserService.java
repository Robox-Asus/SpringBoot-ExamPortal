package com.exam.service;

import java.util.Set;

import com.exam.model.User;
import com.exam.model.UserRole;

public interface UserService {

	//creation User
	public User createUser(User user,Set<UserRole> userRole);
	
	//getUser
	public User getUser(String username);
	
	//delete User
	public void deleteUser(Long id);
	
	//update User
	public User udpateUser(User user);
}
