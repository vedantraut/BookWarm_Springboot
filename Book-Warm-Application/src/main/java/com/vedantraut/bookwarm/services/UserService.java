package com.vedantraut.bookwarm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	public String registerUser(UserDTO userdto) {
		
		Optional<Users> existingUser = userrepository.findByEmail(userdto.getEmail());
		
		if(existingUser.isPresent()) {
			throw new UserExistsException("Email already registered");
		}
		
		Users user = new Users();
		
		user.setUserName(userdto.getUserName());
		user.setEmail(userdto.getEmail());
//		user.setPassword(userdto.getPassword()); // Without Encryption 
		user.setRole(userdto.getRole());;
		
		System.out.println("Encrypted Password - "+passwordEncoder.encode(userdto.getPassword()));
		
		user.setPassword(passwordEncoder.encode(userdto.getPassword())); // Encrypting the password
		
		userrepository.save(user);
		
		return "User created successfully!";
	}


	public String loginUser(String email, String password) {
		
		Users user = userrepository.findByEmail(email)
					  .orElseThrow(() -> new UserNotFoundException("User Not Found"));
					  
		
		if(!passwordEncoder.matches(password, user.getPassword())) {
			throw new RuntimeException("Invalid Password!");
		}
		
//		if(!user.getPassword().equals(password)) {
//			throw new RuntimeException("Invalid Password!");
//		}
		
		
		return "Access Success!";
	}
}
