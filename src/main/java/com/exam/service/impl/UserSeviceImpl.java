package com.exam.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.exam.model.User;
import com.exam.model.UserRole;
import com.exam.repo.RoleRepository;
import com.exam.repo.UserRepository;
import com.exam.service.UserService;

@Service
public class UserSeviceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;


	@Override
	public User createUser(User user, Set<UserRole> userRole) {
		// TODO Auto-generated method stub
		User local = userRepository.findByUsername(user.getUsername());
		if (local == null) {
			for (UserRole roles : userRole) {
				roleRepository.save(roles.getRole());
			}
			user.getUserRoles().addAll(userRole);
			local = userRepository.save(user);
		}
		return local;
	}

	@Override
	public User getUser(String username) {
		// TODO Auto-generated method stub
		return userRepository.findByUsername(username);
	}

	@Override
	public void deleteUser(Long id) {
		// TODO Auto-generated method stub
		userRepository.deleteById(id);

	}

	@Override
	public User udpateUser(User user) {
		// TODO Auto-generated method s)tub
		User local = userRepository.findByUsername(user.getUsername());
		if (local != null) {
			return userRepository.save(user);
		}
		return null;
	}

}
