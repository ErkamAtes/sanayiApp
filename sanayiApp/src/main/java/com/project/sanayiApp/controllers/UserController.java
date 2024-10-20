package com.project.sanayiApp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.sanayiApp.business.abstracts.UserService;
import com.project.sanayiApp.entities.User;

@RestController
@RequestMapping("/users")
public class UserController {
	
	private UserService userService;
	
	public UserController(UserService userService) {
		this.userService = userService;
	}
	
	
	@GetMapping
	public List<User> getAllUsers(){
		return userService.getAllUsers();
	}
	
	
	@PostMapping
	public User createUser(@RequestBody User newUser) {
		return userService.createUser(newUser);
	}
	
	@GetMapping("/{userId}")
	public User GetUserById(@PathVariable Long userId) {
		//custom exception
		return userService.getUserById(userId);
	}
	
	@PutMapping("/{userId}")
	public User updateUserById(@PathVariable Long userId, @RequestBody User newUser) {
		return userService.updateUser(userId, newUser);
	}
	@DeleteMapping("/{userId}")
	public void deleteUserById(@PathVariable Long userId) {
		userService.deleteById(userId);
	}
	
}
