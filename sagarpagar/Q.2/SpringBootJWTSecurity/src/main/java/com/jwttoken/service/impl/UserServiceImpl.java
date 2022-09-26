package com.jwttoken.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.jwttoken.model.User;
import com.jwttoken.rpo.UserRepoitory;
import com.jwttoken.service.IUserService;

@Service
public class UserServiceImpl implements IUserService, UserDetailsService {
	
	
	@Autowired
	private UserRepoitory repo;
	@Autowired
	private BCryptPasswordEncoder encoder1;

	
	@Override
	public Integer saveUser(User user) {
		String pwd = user.getPwd();
		pwd = encoder1.encode(pwd);
		user.setPwd(pwd);
		return repo.save(user).getId();
	}

	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
	
		User user = repo.findByUsername(username);
		
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		List<String> roles = user.getRoles();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority(role));
		}

		
		org.springframework.security.core.userdetails.User urs = new org.springframework.security
				.core.userdetails.User(
				user.getUsername(), user.getPwd(), authorities);
		return urs;
	}

}
