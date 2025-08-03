package com.vedantraut.bookwarm.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.vedantraut.bookwarm.dtos.AuthenticationRequest;
import com.vedantraut.bookwarm.dtos.AuthenticationResponse;
import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.entity.Users;
import com.vedantraut.bookwarm.exceptions.UserExistsException;
import com.vedantraut.bookwarm.exceptions.UserNotFoundException;
import com.vedantraut.bookwarm.repository.UserRepository;
import com.vedantraut.bookwarm.security.JWTService;

import jakarta.validation.Valid;

@Service
public class AuthenticationService {
	
	@Autowired
	UserRepository userrepository;
	
	@Autowired
	JWTService jwtservice;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;


	public AuthenticationResponse loginUser(AuthenticationRequest request) {
		
		Users user = userrepository.findByEmail(request.getEmailId())
					  .orElseThrow(() -> new UserNotFoundException("User Not Found"));
					  
		
		if(!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
			throw new RuntimeException("Invalid Password!");
		}
		
//		if(!user.getPassword().equals(password)) {
//			throw new RuntimeException("Invalid Password!");
//		}
		
		String token = jwtservice.generateToken(user.getEmail());
		
		return new AuthenticationResponse(token, "Login Successful!");
	}
}
