package com.project.sanayiApp.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.project.sanayiApp.business.abstracts.UserService;
import com.project.sanayiApp.entities.User;
import com.project.sanayiApp.repository.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserManager implements UserService{
	
	private UserRepository userRepository;
	
	
	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return userRepository.findAll();
	}

	@Override
	public User createUser(User newUser) {
		// TODO Auto-generated method stub
		return userRepository.save(newUser);
	}

	@Override
	public User getUserById(Long userId) {
		// TODO Auto-generated method stub
		return userRepository.findById(userId).orElse(null);
	}

	@Override
	public User updateUser(Long userId, User newUser) {
		Optional<User> user = userRepository.findById(userId);
		if(user.isPresent()) {
			User foundUser = user.get();
			foundUser.setUserName(newUser.getUserName());
			foundUser.setPassword(newUser.getPassword());
			userRepository.save(foundUser);
			return foundUser;
		}
		else
			return null;
	}

	@Override
	public void deleteById(Long userId) {
		userRepository.deleteById(userId);
		
	}

	@Override
	public User getUserByUserName(String userName) {
		
		
		
		return userRepository.findByUserName(userName);
	}

}
