package com.vedantraut.bookwarm.controllers;

import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.AuthenticationRequest;
import com.vedantraut.bookwarm.dtos.AuthenticationResponse;
import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.services.AuthenticationService;
import com.vedantraut.bookwarm.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userservice;

	@Autowired
	AuthenticationService authservice;
	
	@GetMapping("/{userId}")
	public ResponseEntity<UserDTO> getUser(@PathVariable long userId) {
		UserDTO userDTO = userservice.getUserDetails(userId);
	
		return ResponseEntity.ok(userDTO);
	}
	
	@PostMapping("/register")
	public ResponseEntity<Map<String, String>> registerUser(@RequestBody @Valid UserDTO userdto) {
		String responseMsg = userservice.registerUser(userdto);
		
		Map<String, String> response = new HashMap<>();
		
		response.put("message", responseMsg);
		
		return ResponseEntity.ok(response);
	}
	
	@PostMapping("/login")
	public ResponseEntity<AuthenticationResponse> loginUser(@RequestBody AuthenticationRequest request) {
		
		System.out.println(request);
		
		AuthenticationResponse responseMsg = authservice.loginUser(request);
		
		return ResponseEntity.ok(responseMsg);
	}
	
	
}
