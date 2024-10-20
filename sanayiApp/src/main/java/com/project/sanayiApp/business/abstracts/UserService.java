package com.project.sanayiApp.business.abstracts;

import java.util.List;

import com.project.sanayiApp.entities.User;

public interface UserService {
	
	public List<User> getAllUsers();
	public User createUser(User newUser);
	public User getUserById(Long userId);
	public User updateUser(Long userId, User newUser); 
	public void deleteById(Long userId);
	public User getUserByUserName(String userName);
}
