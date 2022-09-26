package com.jwttoken.rpo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.jwttoken.model.User;

public interface UserRepoitory extends JpaRepository<User, Integer> {
	User findByUsername(String username);

}
