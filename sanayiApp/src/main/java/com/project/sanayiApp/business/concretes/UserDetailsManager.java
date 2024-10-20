package com.project.sanayiApp.business.concretes;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.project.sanayiApp.entities.User;
import com.project.sanayiApp.repository.UserRepository;
import com.project.sanayiApp.security.JwtUserDetails;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserDetailsManager implements UserDetailsService {

	
	private UserRepository userRepository;
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		User user = userRepository.findByUserName(username);
		if(user == null) {
			throw new UsernameNotFoundException("Kullanıcı Bulunamadı");
		}
		return JwtUserDetails.create(user);
	}
	
	public UserDetails loadUserById(Long id) {
		User user = userRepository.findById(id).get();
		return JwtUserDetails.create(user);
	}

}
