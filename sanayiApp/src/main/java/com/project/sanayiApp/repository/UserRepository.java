package com.project.sanayiApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.sanayiApp.entities.User;

public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
