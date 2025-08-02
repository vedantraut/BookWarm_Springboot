package com.vedantraut.bookwarm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.entity.Users;
import com.vedantraut.bookwarm.exceptions.UserExistsException;
import com.vedantraut.bookwarm.exceptions.UserNotFoundException;
import com.vedantraut.bookwarm.repository.UserRepository;

import jakarta.validation.Valid;

@Service
public class UserService {
	
	@Autowired
	UserRepository userrepository;

	public String registerUser(UserDTO userdto) {
		
		Optional<Users> existingUser = userrepository.findByEmail(userdto.getEmail());
		
		if(existingUser.isPresent()) {
			throw new UserExistsException("Email already registered");
		}
		
		Users user = new Users();
		
		user.setUserName(userdto.getUserName());
		user.setPassword(userdto.getPassword()); // Encryption pending
		user.setEmail(userdto.getEmail());
		
		userrepository.save(user);
		
		return "User created successfully!";
	}


	public String loginUser(String email, String password) {
		
		Users user = userrepository.findByEmail(email)
					  .orElseThrow(() -> new UserNotFoundException("User Not Found"));
					  
		
		if(!user.getPassword().equals(password)) {
			throw new RuntimeException("Invalid Password!");
		}
		
		
		return "Access Success!";
	}
}
