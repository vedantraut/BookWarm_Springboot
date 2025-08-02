package com.vedantraut.bookwarm.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vedantraut.bookwarm.dtos.AuthorDTO;
import com.vedantraut.bookwarm.dtos.UserDTO;
import com.vedantraut.bookwarm.entity.Author;
import com.vedantraut.bookwarm.services.AuthorService;
import com.vedantraut.bookwarm.services.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
	@Autowired
	UserService userservice;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerUser(@RequestBody @Valid UserDTO userdto) {
		String responseMsg = userservice.registerUser(userdto);
		
		return ResponseEntity.ok(responseMsg);
	}
	
	@PostMapping("/login")
	public ResponseEntity<String> loginUser(@RequestParam String email, @RequestParam String password) {
		String responseMsg = userservice.loginUser(email, password);
		
		return ResponseEntity.ok(responseMsg);
	}
}
