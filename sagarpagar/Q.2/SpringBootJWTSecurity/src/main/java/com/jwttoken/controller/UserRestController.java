package com.jwttoken.controller;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.jwttoken.model.User;
import com.jwttoken.model.UserRequest;
import com.jwttoken.model.UserResponse;
import com.jwttoken.service.IUserService;
import com.jwttoken.util.JwtUtil;

@RestController
@RequestMapping("/user")
public class UserRestController {
	@Autowired
	private IUserService service;
	@Autowired
	private AuthenticationManager manager;
	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/save")
	public ResponseEntity<String> saveUser(@RequestBody User user) {
		Integer id = service.saveUser(user);
		return ResponseEntity.ok("User Saved with id : " + id);
	}

	
	@PostMapping("/login")
	public ResponseEntity<UserResponse> loginCheck(@RequestBody UserRequest userReq) {
		System.out.println("Login Method in Controller");

		
		manager.authenticate(new UsernamePasswordAuthenticationToken(userReq.getUsername(), userReq.getPassword()));
		

		String token = jwtUtil.generateToken(userReq.getUsername());
		
		return ResponseEntity.ok(new UserResponse("SUCCESS", token));
	}

	
	@GetMapping("/inbox")
	public String showInbox(Principal p) {
		return "Welcome to User " + p.getName();
	}
}
