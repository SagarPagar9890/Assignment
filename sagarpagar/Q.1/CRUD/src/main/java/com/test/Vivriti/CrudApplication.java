package com.test.Vivriti;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.Vivriti.model.User;
import com.test.Vivriti.repository.UserRepository;

@SpringBootApplication
public class CrudApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(CrudApplication.class, args);
	}
	
	@Autowired
	private UserRepository userRepository;
	
	
	
	@Override
	public void run(String... args) throws Exception {
		
		User user = new User();
		user.setFirstName("sagar");
		user.setLastName("pagar");
		user.setDob("21/05/1994");
		user.setUsername("sagp");
		user.setPassword("123");
		userRepository.save(user);
		
		User user1 = new User();
		user1.setFirstName("sachin");
		user1.setLastName("kolhe");
		user1.setDob("14/03/1996");
		user1.setUsername("sachin");
		user1.setPassword("12345");
		userRepository.save(user1);
		

		
	}

}
