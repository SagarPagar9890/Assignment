package com.test.Vivriti.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.test.Vivriti.exception.ResourceNotFoundException;
import com.test.Vivriti.model.User;
import com.test.Vivriti.repository.UserRepository;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	// create user
	@PostMapping
	public User createUser(@RequestBody User user) {
		return userRepository.save(user);
	}

	// get user
	@GetMapping("{id}")
	public ResponseEntity<User> getUserById(@PathVariable long id) {
		User user = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id" + id));
		return ResponseEntity.ok(user);
	}

	// update user
	@PutMapping("{id}")
	public ResponseEntity<User> updateUser(@PathVariable long id, @RequestBody User userDetails) {
		User updateUser = userRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("User not exist with id" + id));
		updateUser.setFirstName(userDetails.getFirstName());
		updateUser.setLastName(userDetails.getLastName());
		updateUser.setDob(userDetails.getDob());
		updateUser.setUsername(userDetails.getUsername());
		updateUser.setPassword(userDetails.getPassword());

		userRepository.save(updateUser);

		return ResponseEntity.ok(updateUser);
	}
	
	//delete User
	@DeleteMapping("{id}")
	public ResponseEntity<HttpStatus> deleteUser(@PathVariable long id){
		User user = userRepository.findById(id)
				.orElseThrow(()->new ResourceNotFoundException("User not exist with id"+id));
		userRepository.delete(user);
		
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
		
	}

}
